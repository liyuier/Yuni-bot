package com.yuier.yuni.core.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.FunctionCallerDetector;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.enums.FuncBaseCallerEnum;
import com.yuier.yuni.common.enums.OneBotEventEnum;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.EventLogUtils;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.core.domain.global.GlobalData;
import com.yuier.yuni.core.service.MessageRecordService;
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

//@Slf4j
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
    GlobalData globalData;

    @Autowired
    EventLogUtils eventLogUtils;

    String[] callers = {
            FuncBaseCallerEnum.AT.toString(),
            FuncBaseCallerEnum.ORDER.toString(),
            FuncBaseCallerEnum.KEYWORD.toString(),
            FuncBaseCallerEnum.REGULAR.toString()
    };

    public ResponseResult handle(ObjectNode postEventNode) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ExecutionException, InterruptedException {
        globalData.setPostEventNode(postEventNode);  // TODO 把这玩意移到 AOP 里边去
        MessageEvent messageEvent = messageEventService.postToMessage(postEventNode, MessageEvent.class);
        eventLogUtils.printRcvMsgEventLog(messageEvent);
        MessageChain chain = messageEvent.getMessage();
        detect(chain, messageEvent);
        return ResponseResult.okResult();
    }

    private void detect(MessageChain chain, MessageEvent messageEvent) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ExecutionException, InterruptedException {
        Map<String, Object> handlerBeans = applicationContext.getBeansWithAnnotation(FunctionCallerDetector.class);
        for (String caller : callers) {
            for (Object bean : handlerBeans.values()) {
                FunctionCallerDetector annotation = bean.getClass().getAnnotation(FunctionCallerDetector.class);
                if (annotation.callerKind().toString().equals(caller)) {
                    Object o = asyncService.asyncReflective(bean, chain, SystemConstants.PLUGIN_ENTRY_NAME.MSG_DETECTOR_ENTRY).get();
                }
            }
        }
    }
}
