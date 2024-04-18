package com.yuier.yuni.core.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.FunctionCallerDetector;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.core.domain.global.GlobalData;
import com.yuier.yuni.core.service.MessageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
@OneBotEventHandler(eventType = SystemConstants.ONE_BOT_POST_TYPE.MESSAGE)
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
    GlobalData globalData;

    String[] callers = {
            SystemConstants.FUNCTION_KIND.AT_CALL,
            SystemConstants.FUNCTION_KIND.ORDER_CALL,
            SystemConstants.FUNCTION_KIND.KEY_WORD_CALL,
            SystemConstants.FUNCTION_KIND.REGULAR_CALL
    };

    public ResponseResult handle(ObjectNode postEventNode) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ExecutionException, InterruptedException {
        log.info("进入了消息事件处理器");
        globalData.setPostEventNode(postEventNode);  // TODO 把这玩意移到 AOP 里边去
        MessageEvent messageEvent = messageEventService.postToMessage(postEventNode, MessageEvent.class);
        MessageChain chain = messageChainService.buildChain((ArrayNode) postEventNode.get("message"));
        detect(chain, messageEvent);
        return ResponseResult.okResult();
    }

    private void detect(MessageChain chain, MessageEvent messageEvent) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ExecutionException, InterruptedException {
        Map<String, Object> handlerBeans = applicationContext.getBeansWithAnnotation(FunctionCallerDetector.class);
        for (String caller : callers) {
            for (Object bean : handlerBeans.values()) {
                FunctionCallerDetector annotation = bean.getClass().getAnnotation(FunctionCallerDetector.class);
                if (annotation.callerKind().equals(caller)) {
//                    Object o = asyncService.asyncReflectiveDetector(bean, chain).get();
                    Object o = asyncService.asyncReflective(bean, chain, "detect").get();
                }
            }
        }
    }
}
