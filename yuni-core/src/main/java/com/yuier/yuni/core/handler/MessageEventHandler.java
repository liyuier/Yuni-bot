package com.yuier.yuni.core.handler;

import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.dto.OneBotPostEventDto;
import org.springframework.stereotype.Component;

/**
 * @Title: MessageEventHandler
 * @Author yuier
 * @Package com.yuier.yuni.core.handler
 * @Date 2024/4/11 1:48
 * @description: 消息事件处理器
 */
@Component
public class MessageEventHandler {

    public ResponseResult handle(OneBotPostEventDto oneBotPostEventDto) {

        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }
}
