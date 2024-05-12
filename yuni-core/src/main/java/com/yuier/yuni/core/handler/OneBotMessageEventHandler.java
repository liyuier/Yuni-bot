package com.yuier.yuni.core.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.dto.CallBaseFunctionPluginDto;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.enums.OneBotEventEnum;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.CallFunction;
import com.yuier.yuni.common.utils.EventLogUtils;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.core.domain.global.detector.PluginForDetect;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.domain.global.detector.base.BasePluginForDetect;
import com.yuier.yuni.core.domain.global.detector.order.OrderPluginForDetect;
import com.yuier.yuni.core.service.MessageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Title: OneBotMessageEventHandler
 * @Author yuier
 * @Package com.yuier.yuni.core.handlers
 * @Date 2024/4/11 22:47
 * @description: OneBot 上报消息事件处理器
 */

@Slf4j
@Component
@OneBotEventHandler(eventType = OneBotEventEnum.MESSAGE)
public class OneBotMessageEventHandler {

    @Autowired
    MessageEventService messageEventService;

    @Autowired
    MessageRecordService messageRecordService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MessageChainService messageChainService;

    @Autowired
    AsyncService asyncService;

    @Autowired
    CoreGlobalData coreGlobalData;

    @Autowired
    EventLogUtils eventLogUtils;

    @Autowired
    CallFunction callFunction;

    public ResponseResult handle(ObjectNode postEventNode) {
        MessageEvent messageEvent = messageEventService.postToMessage(postEventNode, MessageEvent.class);
        eventLogUtils.printRcvMsgEventLog(messageEvent);
        MessageChain chain = messageEvent.getMessage();
        if (!detectForOrderPlugin(chain, postEventNode, messageEvent)) {
            detectForBasePlugin(chain, postEventNode, messageEvent);
        }
        return ResponseResult.okResult();
    }

    private Boolean detectForOrderPlugin(MessageChain chain, ObjectNode postEventNode, MessageEvent messageEvent) {
        Boolean flag = false;
        HashMap<String, PluginForDetect> pluginMap = coreGlobalData.getOrderPlugins().getPluginMap();
        for (String pluginId : pluginMap.keySet()) {
            OrderPluginForDetect pluginForDetect = (OrderPluginForDetect) pluginMap.get(pluginId);
            OrderMatchedOut orderMatchedOut = new OrderMatchedOut();
            if (pluginForDetect.hitListener(messageEvent) && pluginForDetect.hitDetector(chain, orderMatchedOut, coreGlobalData.getOrderMark().toString())) {
                log.info("命中插件 " + pluginId);
                callFunction.callBaseFunctionPlugin(new CallBaseFunctionPluginDto(pluginId, postEventNode));
            }
        }
        return flag;
    }

    private void detectForBasePlugin(MessageChain chain, ObjectNode postEventNode, MessageEvent messageEvent) {
        HashMap<String, PluginForDetect> pluginMap = coreGlobalData.getBasePlugins().getPluginMap();
        for (String pluginId : pluginMap.keySet()) {
            BasePluginForDetect pluginForDetect = (BasePluginForDetect) pluginMap.get(pluginId);
            if (pluginForDetect.hitListener(messageEvent) && pluginForDetect.hitDetector(chain)) {
                log.info("命中插件 " + pluginId);
                callFunction.callBaseFunctionPlugin(new CallBaseFunctionPluginDto(pluginId, postEventNode));
            }
        }
    }
}
