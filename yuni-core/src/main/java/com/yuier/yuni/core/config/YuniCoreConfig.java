package com.yuier.yuni.core.config;

import com.yuier.yuni.common.aspect.OneBotPostEventDispatchAspect;
import org.springframework.context.annotation.Bean;
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
public class YuniCoreConfig {

    @Bean
    public OneBotPostEventDispatchAspect oneBotPostEventDispatchAspect() {
        return new OneBotPostEventDispatchAspect();
    }

}
