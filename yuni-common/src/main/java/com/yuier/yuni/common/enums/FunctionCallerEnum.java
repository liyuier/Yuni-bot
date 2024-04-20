package com.yuier.yuni.common.enums;

/**
 * @Title: FunctionCallerEnum
 * @Author yuier
 * @Package com.yuier.yuni.common.enums
 * @Date 2024/4/20 18:42
 * @description: 功能调用方式的枚举类（指令触发、关键字触发、正则表达式触发、at 触发等）
 */
public enum FunctionCallerEnum {

    ORDER("order"),
    KEYWORD("keyword"),
    AT("at"),
    REGULAR("regular"),
    POSITIVE("positive");

    private String callerKind;

    FunctionCallerEnum(String callerKind) {
        this.callerKind = callerKind;
    }

    @Override
    public String toString() {
        return this.callerKind;
    }
}
