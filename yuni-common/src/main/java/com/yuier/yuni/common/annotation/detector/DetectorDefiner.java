package com.yuier.yuni.common.annotation.detector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: BaseDetector
 * @Author yuier
 * @Package com.yuier.yuni.common.annotation.detector
 * @Date 2024/5/4 4:25
 * @description: 基础消息链处理器注解，加在插件上
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DetectorDefiner {
}
