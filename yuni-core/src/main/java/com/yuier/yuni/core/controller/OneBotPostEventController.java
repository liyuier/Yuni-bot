package com.yuier.yuni.core.controller;

import com.yuier.yuni.common.annotation.OneBotEventEntrance;
import com.yuier.yuni.common.annotation.OneBotPostEntrance;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.dto.OneBotPostEventDto;
import com.yuier.yuni.core.handler.MessageEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OneBotPostEventController {

    @Autowired
    MessageEventHandler messageEventHandler;

    @PostMapping("/")
    @OneBotPostEntrance
    public ResponseResult eventUnifiedEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("收到无法识别事件 " + oneBotPostEventDto.getPostType());
        return ResponseResult.okResult("无法识别事件 " + oneBotPostEventDto.getPostType());
    }

    @OneBotEventEntrance(eventType = SystemConstants.ONE_BOT_POST_TYPE.MESSAGE)
    public ResponseResult messageEventEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了消息事件处理逻辑");
        return messageEventHandler.handle(oneBotPostEventDto);
    }

    @OneBotEventEntrance(eventType = SystemConstants.ONE_BOT_POST_TYPE.NOTICE)
    public ResponseResult noticeEventEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了通知事件处理逻辑");
        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }

    @OneBotEventEntrance(eventType = SystemConstants.ONE_BOT_POST_TYPE.REQUEST)
    public ResponseResult requestEventEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了请求事件处理逻辑");
        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }

    @OneBotEventEntrance(eventType = SystemConstants.ONE_BOT_POST_TYPE.META)
    public ResponseResult metaEventEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了元事件处理逻辑");
        return ResponseResult.okResult("进入了消息事件处理逻辑");
    }

}
