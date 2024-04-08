package com.yuier.yuni.core.controller;

import com.yuier.yuni.core.annotation.OneBotEventController;
import com.yuier.yuni.core.annotation.OneBotEventHandler;
import com.yuier.yuni.core.annotation.OneBotEventUnifiedEntrance;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OneBotEventController
@RequestMapping("/")
public class OneBotPostEventController {

    @PostMapping("/")
    @OneBotEventUnifiedEntrance
    public void eventUnifiedEntrance(HttpServletRequest request) {
        System.out.println(request.getParameter("group_id"));
        System.out.println("收到事件");
    }

    @OneBotEventHandler("message")
    public void messageEventHandler(HttpServletRequest request) {
        System.out.println("进入了消息事件处理逻辑");
    }

    @OneBotEventHandler("notice")
    public void noticeEventHandler(HttpServletRequest request) {
        System.out.println("进入了通知事件处理逻辑");
    }

    @OneBotEventHandler("request")
    public void requestEventHandler(HttpServletRequest request) {
        System.out.println("进入了请求事件处理逻辑");
    }

    @OneBotEventHandler("meta_event")
    public void metaEventHandler(HttpServletRequest request) {
        System.out.println("进入了元事件处理逻辑");
    }

}
