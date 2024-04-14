package com.yuier.yuni.core.config;

import com.yuier.yuni.common.aspect.OneBotPostEventDispatchAspect;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.service.impl.MessageChainServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Title: AopConfig
 * @Author yuier
 * @Package com.yuier.yuni.core.config
 * @Date 2024/4/10 0:31
 * @description: AOP配置类
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {MessageChainServiceImpl.class, OneBotPostEventDispatchAspect.class, TextData.class})
public class YuniCoreConfig {

}
