package com.yuier.yuni.core.controller;

import com.yuier.yuni.core.annotation.OneBotEventController;
import com.yuier.yuni.core.annotation.OneBotEventHandler;
import com.yuier.yuni.core.annotation.OneBotEventUnifiedEntrance;
import com.yuier.yuni.core.domain.dto.OneBotPostEventDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OneBotEventController
@RequestMapping("/")
public class OneBotPostEventController {

    @PostMapping("/")
    @OneBotEventUnifiedEntrance
    public void eventUnifiedEntrance(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("收到无法识别事件 " + oneBotPostEventDto.getPost_type());
    }

    @OneBotEventHandler("message")
    public void messageEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了消息事件处理逻辑");
    }

    @OneBotEventHandler("notice")
    public void noticeEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了通知事件处理逻辑");
    }

    @OneBotEventHandler("request")
    public void requestEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了请求事件处理逻辑");
    }

    @OneBotEventHandler("meta_event")
    public void metaEventHandler(@RequestBody OneBotPostEventDto oneBotPostEventDto) {
        System.out.println("进入了元事件处理逻辑");
    }

}
