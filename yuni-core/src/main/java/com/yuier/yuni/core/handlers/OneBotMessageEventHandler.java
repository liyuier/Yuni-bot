package com.yuier.yuni.core.handlers;

import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.dto.OneBotPostEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    public ResponseResult handle(OneBotPostEventDto oneBotPostEventDto) {
        log.info("进入了消息事件处理器");
        return ResponseResult.okResult();
    }
}
