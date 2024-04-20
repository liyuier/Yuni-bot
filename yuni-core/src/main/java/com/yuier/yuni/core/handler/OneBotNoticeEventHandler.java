package com.yuier.yuni.core.handler;

import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.enums.OneBotEventEnum;
import com.yuier.yuni.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Title: OneBotNoticeEventHnadler
 * @Author yuier
 * @Package com.yuier.yuni.core.handlers
 * @Date 2024/4/11 22:48
 * @description: OneBot 通知事件处理器
 */
@Slf4j
@Component
@OneBotEventHandler(eventType = OneBotEventEnum.NOTICE)
public class OneBotNoticeEventHandler {

    public ResponseResult handle(Map<String, Object> oneBotPostEventDto) {
        log.info("进入了通知事件处理器");
        return ResponseResult.okResult();
    }
}
