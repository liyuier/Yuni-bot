package com.yuier.yuni.core.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.dto.OneBotPostEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Title: OneBotMetaEventHndler
 * @Author yuier
 * @Package com.yuier.yuni.core.handlers
 * @Date 2024/4/11 22:50
 * @description: OneBot 元事件处理器
 */
@Slf4j
@Component
@OneBotEventHandler(eventType = SystemConstants.ONE_BOT_POST_TYPE.META)
public class OneBotMetaEventHandler {

    public ResponseResult handle(Map<String, Object> oneBotPostEventDto) {
        log.info("进入了元事件处理器");
        return ResponseResult.okResult();
    }
}
