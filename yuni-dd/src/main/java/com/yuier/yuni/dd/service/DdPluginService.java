package com.yuier.yuni.dd.service;

import com.yuier.yuni.common.enums.YuniModuleEnum;

/**
 * @Title: PluginService
 * @Author yuier
 * @Package com.yuier.yuni.function.service
 * @Date 2024/4/16 19:34
 * @description: 插件服务接口
 */
public interface DdPluginService {

    void scanAndBuildPlugin();

    void scanAndBuildPlugin(YuniModuleEnum module);
}
