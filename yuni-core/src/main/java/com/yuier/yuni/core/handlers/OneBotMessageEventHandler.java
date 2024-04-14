package com.yuier.yuni.core.handlers;

import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.common.domain.message.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

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

    public ResponseResult handle(Map<String, Object> postEventDto) {
        log.info("进入了消息事件处理器");
        MessageEvent messageEvent = messageEventService.postToMessage(postEventDto, MessageEvent.class);
        return ResponseResult.okResult();
    }
}
