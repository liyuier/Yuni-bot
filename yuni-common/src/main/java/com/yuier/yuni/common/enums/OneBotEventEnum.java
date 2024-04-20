package com.yuier.yuni.common.enums;

/**
 * @Title: OneBotEventEnum
 * @Author yuier
 * @Package com.yuier.yuni.common.enums
 * @Date 2024/4/20 18:56
 * @description: OneBot 实现上报事件类型枚举类
 */
public enum OneBotEventEnum {

    MESSAGE("message"),
    NOTICE("notice"),
    REQUEST("request"),
    META("meta_event");


    private final String eventType;

    OneBotEventEnum(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return this.eventType;
    }
}
