package com.yuier.yuni.core.service;

import com.yuier.yuni.common.enums.YuniModuleEnum;

/**
 * @Title: CorePluginService
 * @Author yuier
 * @Package com.yuier.yuni.core.service
 * @Date 2024/5/29 22:21
 * @description: 核心模块插件服务
 */
public interface CorePluginService {
    void scanAndBuildPlugin(YuniModuleEnum module);
    Boolean pluginExists(String pluginId);
    Boolean positivePluginExists(String pluginId);

    Boolean pluginIsCore(String pluginId);
}
