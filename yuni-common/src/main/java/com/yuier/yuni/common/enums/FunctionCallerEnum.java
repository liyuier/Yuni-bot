package com.yuier.yuni.common.enums;

/**
 * @Title: FunctionCallerEnum
 * @Author yuier
 * @Package com.yuier.yuni.common.enums
 * @Date 2024/4/20 18:42
 * @description: 功能调用方式的枚举类（指令触发、关键字触发、正则表达式触发、at 触发等）
 */
public enum FunctionCallerEnum {

    ORDER("order", "指令触发"),
    KEYWORD("keyword", "关键词触发"),
    AT("at", "@ 本机器人触发"),
    REGULAR("regular", "正则表达式触发"),
    POSITIVE("positive", "主动触发");

    private final String callerKind;
    private final String description;

    FunctionCallerEnum(String callerKind, String description) {
        this.callerKind = callerKind;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.callerKind;
    }

    public String getCallerKind() {
        return callerKind;
    }

    public String getDescription() {
        return description;
    }
}
