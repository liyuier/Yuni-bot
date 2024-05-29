package com.yuier.yuni.core.runner;

import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.core.service.CorePluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Title: CorePluginScanRunner
 * @Author yuier
 * @Package com.yuier.yuni.core.runner
 * @Date 2024/5/29 22:19
 * @description: 核心模块插件扫描
 */
@Component
public class CorePluginScanRunner implements CommandLineRunner{

    @Autowired
    CorePluginService corePluginService;


    @Override
    public void run(String... args) throws Exception {
        corePluginService.scanAndBuildPlugin(YuniModuleEnum.CORE);
    }
}
