package com.yuier.yuni.function.config;

import com.yuier.yuni.common.aspect.OneBotPostEventDispatchAspect;
import com.yuier.yuni.common.config.YuniCommonConfig;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.service.impl.MessageChainServiceImpl;
import com.yuier.yuni.common.utils.CallOneBot;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Title: YuniFunctionConfig
 * @Author yuier
 * @Package com.yuier.yuni.function.config
 * @Date 2024/4/15 0:04
 * @description: function 模块配置类
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {
        MessageChainServiceImpl.class,
        OneBotPostEventDispatchAspect.class,
        TextData.class,
        YuniCommonConfig.class,
        CallOneBot.class
})
public class YuniFunctionConfig {
}
