package com.yuier.yuni.core.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.annotation.OneBotPostEntrance;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class OneBotPostEventController {

    @PostMapping("/")
    @OneBotPostEntrance
    public ResponseResult postEventEntrance(@RequestBody JsonNode oneBotPostEventDto) {
        log.warn("收到无法识别事件 " + oneBotPostEventDto.get(SystemConstants.POST_KEY_FIELD.POST_TYPE));
        return ResponseResult.okResult("无法识别事件 " + oneBotPostEventDto.get("post_type"));
    }
}
