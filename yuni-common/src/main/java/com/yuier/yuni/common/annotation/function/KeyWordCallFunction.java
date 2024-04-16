package com.yuier.yuni.common.annotation.function;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: KeyWordFunction
 * @Author yuier
 * @Package com.yuier.yuni.common.annotation
 * @Date 2024/4/15 1:31
 * @description: 注解，加在关键词触发的功能插件上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyWordCallFunction {
    String keyWord();
}
