package com.yuier.yuni.function.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Title: PluginScanRunner
 * @Author yuier
 * @Package com.yuier.yuni.function.runner
 * @Date 2024/4/16 19:58
 * @description: 启动时自动扫描插件
 */
@Component
public class PluginScanRunner {

    @Autowired
    ApplicationContext applicationContext;
}
