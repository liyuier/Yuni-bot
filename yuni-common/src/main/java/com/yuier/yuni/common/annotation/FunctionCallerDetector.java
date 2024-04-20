package com.yuier.yuni.common.annotation;

import com.yuier.yuni.common.enums.FunctionCallerEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: FunctionCallerDetector
 * @Author yuier
 * @Package com.yuier.yuni.common.annotation
 * @Date 2024/4/16 22:49
 * @description: 注解，加在功能调用探测器上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionCallerDetector {
    FunctionCallerEnum callerKind();
}
