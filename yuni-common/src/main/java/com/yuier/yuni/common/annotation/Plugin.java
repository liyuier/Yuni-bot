package com.yuier.yuni.common.annotation;

import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.enums.PermissionLevelEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: Plugin
 * @Author yuier
 * @Package com.yuier.yuni.common.annotation
 * @Date 2024/5/4 13:11
 * @description: 插件注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
    String id();
    MessageTypeEnum listener();
    PermissionLevelEnum permission() default PermissionLevelEnum.USER;
}
