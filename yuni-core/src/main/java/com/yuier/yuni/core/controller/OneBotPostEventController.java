package com.yuier.yuni.core.controller;

import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.annotation.OneBotEventEntrance;
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
    @OneBotEventEntrance
    public ResponseResult eventUnifiedEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("收到无法识别事件 " + oneBotPostEventDto.getPostType());
        return ResponseResult.okResult("无法识别事件 " + oneBotPostEventDto.getPostType());
    }

    @OneBotEventHandler("message")
    public ResponseResult messageEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了消息事件处理逻辑");
        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }

    @OneBotEventHandler("notice")
    public ResponseResult noticeEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了通知事件处理逻辑");
        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }

    @OneBotEventHandler("request")
    public ResponseResult requestEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了请求事件处理逻辑");
        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }

    @OneBotEventHandler("meta_event")
    public ResponseResult metaEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了元事件处理逻辑");
        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }

}
