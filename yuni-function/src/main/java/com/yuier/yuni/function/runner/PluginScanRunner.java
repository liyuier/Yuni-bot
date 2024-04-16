package com.yuier.yuni.function.runner;

import com.yuier.yuni.common.utils.CallCore;
import com.yuier.yuni.function.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: PluginScanRunner
 * @Author yuier
 * @Package com.yuier.yuni.function.runner
 * @Date 2024/4/16 19:58
 * @description: 启动时初始化插件，并将插件发送给 core 服务
 */
@Component
public class PluginScanRunner {

    @Autowired
    CallCore callCore;

    @Autowired
    PluginService pluginService;




}
