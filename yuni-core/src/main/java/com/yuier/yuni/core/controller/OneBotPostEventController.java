package com.yuier.yuni.core.controller;

import com.yuier.yuni.common.annotation.OneBotPostEntrance;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.dto.OneBotPostEventDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OneBotPostEventController {

    @PostMapping("/")
    @OneBotPostEntrance
    public ResponseResult eventUnifiedEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("收到无法识别事件 " + oneBotPostEventDto.getPostType());
        return ResponseResult.okResult("无法识别事件 " + oneBotPostEventDto.getPostType());
    }
}
