package com.yuier.yuni.core.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.enums.OneBotEventEnum;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.EventLogUtils;
import com.yuier.yuni.common.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Title: OneBotMessageSentEventHandler
 * @Author yuier
 * @Package com.yuier.yuni.core.handler
 * @Date 2024/5/5 23:31
 * @description: OneBot 自身发送消息事件
 */
@Component
@OneBotEventHandler(eventType = OneBotEventEnum.MESSAGE_SENT)
public class OneBotMessageSentEventHandler {

    @Autowired
    MessageEventService messageEventService;

    @Autowired
    EventLogUtils eventLogUtils;

    public ResponseResult handle(ObjectNode postEventNode) {
        MessageEvent messageEvent = messageEventService.postToMessage(postEventNode, MessageEvent.class);
        eventLogUtils.printSendMsgLog(messageEvent);
        return ResponseResult.okResult();
    }
}
