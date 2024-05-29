package com.yuier.yuni.dd.runner;

import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.dd.service.DdPluginService;
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
public class PluginScanRunner implements CommandLineRunner{

    @Autowired
    DdPluginService ddPluginService;

    @Override
    public void run(String... args){
        ddPluginService.scanAndBuildPlugin(YuniModuleEnum.DD);
    }


}
