package com.yuier.yuni.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: RegularCallFunction
 * @Author yuier
 * @Package com.yuier.yuni.common.annotation
 * @Date 2024/4/15 1:44
 * @description: 注解，加在正则表达式触发的功能插件上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegularCallFunction {
    String regular();
}
