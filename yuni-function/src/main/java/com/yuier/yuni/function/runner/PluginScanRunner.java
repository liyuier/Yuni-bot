package com.yuier.yuni.function.runner;

import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.utils.CallCore;
import com.yuier.yuni.common.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Title: PluginScanRunner
 * @Author yuier
 * @Package com.yuier.yuni.function.runner
 * @Date 2024/4/16 19:58
 * @description: 启动时初始化插件，并将插件发送给 core 服务
 */
@Component
public class PluginScanRunner implements CommandLineRunner {

    @Autowired
    CallCore callCore;

    @Autowired
    PluginService pluginService;

    @Override
    public void run(String... args) {
        PluginFunctionDto dto = pluginService.buildPluginFunctionDto();
        callCore.initializeFunction(dto);
    }


}
