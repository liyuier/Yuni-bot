package com.yuier.yuni.common.annotation.function;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: AtCallFunction
 * @Author yuier
 * @Package com.yuier.yuni.common.annotation.function
 * @Date 2024/4/16 19:31
 * @description: 注解，加在 at 机器人事件触发的功能插件上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AtCallFunction {
}
