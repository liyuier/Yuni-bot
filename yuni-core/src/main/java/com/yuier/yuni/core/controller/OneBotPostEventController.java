package com.yuier.yuni.core.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.annotation.OneBotPostEntrance;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.dto.OneBotPostEventDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class OneBotPostEventController {

    @PostMapping("/")
    @OneBotPostEntrance
    public ResponseResult postEventEntrance(@RequestBody Map<String, Object> oneBotPostEventDto) {
        System.out.println("收到无法识别事件 " + oneBotPostEventDto.get("post_type"));
        return ResponseResult.okResult("无法识别事件 " + oneBotPostEventDto.get("post_type"));
    }
}
