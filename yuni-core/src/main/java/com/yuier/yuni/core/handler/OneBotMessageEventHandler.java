package com.yuier.yuni.core.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.domain.dto.CallFunctionPluginDto;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.enums.OneBotEventEnum;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.CallFunction;
import com.yuier.yuni.common.utils.EventLogUtils;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.core.detector.base.BaseDetectorForUse;
import com.yuier.yuni.core.detector.base.BasePluginDetector;
import com.yuier.yuni.core.detector.listener.MessageTypeListenerForUse;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.service.MessageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

    public ResponseResult handle(ObjectNode postEventNode) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ExecutionException, InterruptedException {
        coreGlobalData.setPostEventNode(postEventNode);  // TODO 把这玩意移到 AOP 里边去
        MessageEvent messageEvent = messageEventService.postToMessage(postEventNode, MessageEvent.class);
        eventLogUtils.printRcvMsgEventLog(messageEvent);
        MessageChain chain = messageEvent.getMessage();
        detectBase(chain, postEventNode, messageEvent);
        return ResponseResult.okResult();
    }


    private void detectBase(MessageChain chain, ObjectNode postEventNode, MessageEvent messageEvent) {
        for (BasePluginDetector basePluginDetector : coreGlobalData.getBasePluginsDetector().getPluginDtoHashMap().values()) {
            MessageTypeListenerForUse listener = basePluginDetector.getListener();
            BaseDetectorForUse detector = basePluginDetector.getDetector();
            if (listener.hit(messageEvent) && detector.hit(chain)) {
                log.info("命中插件" + basePluginDetector.getPluginId());
                callFunction.callFunctionPlugin(new CallFunctionPluginDto(basePluginDetector.getPluginId(), postEventNode));
            }
        }
    }
}
