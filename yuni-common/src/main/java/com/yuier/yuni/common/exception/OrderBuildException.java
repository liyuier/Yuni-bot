package com.yuier.yuni.common.exception;

import lombok.Getter;

/**
 * @Title: OrderBuildException
 * @Author yuier
 * @Package com.yuier.yuni.common.exception
 * @Date 2024/4/21 23:36
 * @description: 指令构建失败异常
 */
@Getter
public class OrderBuildException extends RuntimeException{

    private final String wrongOrderStr;

    public OrderBuildException(String wrongOrderStr) {
        super(wrongOrderStr);
        this.wrongOrderStr = wrongOrderStr;
    }
}
