package com.yuier.yuni.core.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.enums.OneBotEventEnum;
import com.yuier.yuni.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Title: OneBotRequestEventHandler
 * @Author yuier
 * @Package com.yuier.yuni.core.handlers
 * @Date 2024/4/11 22:50
 * @description: OneBot 请求事件处理器
 */
@Slf4j
@Component
@OneBotEventHandler(eventType = OneBotEventEnum.REQUEST)
public class OneBotRequestEventHandler {

    public ResponseResult handle(ObjectNode postEventNode) {
        log.info("进入了请求事件处理器");
        return ResponseResult.okResult();
    }
}
