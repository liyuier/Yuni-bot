package com.yuier.yuni.core.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.dto.CallBaseFunctionPluginDto;
import com.yuier.yuni.common.domain.dto.CallOrderFunctionPluginDto;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageChainForOrder;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.AtData;
import com.yuier.yuni.common.domain.message.data.ReplyData;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.domain.message.dto.GetMessageDto;
import com.yuier.yuni.common.enums.MessageDataEnum;
import com.yuier.yuni.common.enums.OneBotEventEnum;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.CallFunction;
import com.yuier.yuni.common.utils.CallOneBot;
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

    @Autowired
    CallOneBot callOneBot;

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
        // 预处理，将 chain 拆分为便于指令解析的结构
        MessageChainForOrder chainForOrder = splitChainForOrder(chain);
        Boolean flag = false;
        HashMap<String, PluginForDetect> pluginMap = coreGlobalData.getOrderPlugins().getPluginMap();
        for (String pluginId : pluginMap.keySet()) {
            chainForOrder.setCurSegIndex(0);
            OrderPluginForDetect pluginForDetect = (OrderPluginForDetect) pluginMap.get(pluginId);
            OrderMatchedOut orderMatchedOut = new OrderMatchedOut();
            if (pluginForDetect.hitListener(messageEvent) && pluginForDetect.hitDetector(chainForOrder, orderMatchedOut, coreGlobalData.getOrderMark().toString())) {
                log.info("命中插件 " + pluginId);
                callFunction.callOrderFunctionPlugin(new CallOrderFunctionPluginDto(pluginId, postEventNode, orderMatchedOut));
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

    private MessageChainForOrder splitChainForOrder(MessageChain chain) {
        MessageChainForOrder chainForOrder = new MessageChainForOrder();
        for (MessageSeg messageSeg : chain.getContent()) {
            if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                String[] strArr = ((TextData) messageSeg.getData()).getText().split(" ");
                for (String str : strArr) {
                    if (!str.trim().isEmpty()) {
                        chainForOrder.addTextSeg(str);
                    }
                }
            } else {
                chainForOrder.getContent().add(messageSeg);
            }
        }
        /** 如果存在回复消息，进行额外处理
         * 1. 将头部的回复类消息拆下，存放起来
         * 2. 删除一个回复消息自带的 @ 消息
         */
        if (chainForOrder.startWithReplyData()) {
            chainForOrder.setReplyData((ReplyData) chainForOrder.getContent().remove(0).getData());
            Long userId = callOneBot.getMessage(new GetMessageDto(
                    Long.parseLong(chainForOrder.getReplyData().getId())
            )).getUserId();
            for (MessageSeg messageSeg : chainForOrder.getContent()) {
                if (messageSeg.typeOf(MessageDataEnum.AT)) {
                    if (((AtData) messageSeg.getData()).getQq().equals(String.valueOf(userId))) {
                        chainForOrder.getContent().remove(messageSeg);
                        break;
                    }
                }
            }
        }
        return chainForOrder;
    }
}
