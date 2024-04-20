package com.yuier.yuni.common.enums;

/**
 * @Title: OneBotEventEnum
 * @Author yuier
 * @Package com.yuier.yuni.common.enums
 * @Date 2024/4/20 18:56
 * @description: OneBot 实现上报事件类型枚举类
 */
public enum OneBotEventEnum {

    MESSAGE("message", "消息事件"),
    NOTICE("notice", "通知事件"),
    REQUEST("request", "请求事件"),
    META("meta_event", "元事件");


    private final String eventType;
    private final String description;

    OneBotEventEnum(String eventType, String description) {
        this.eventType = eventType;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDescription() {
        return description;
    }
}
