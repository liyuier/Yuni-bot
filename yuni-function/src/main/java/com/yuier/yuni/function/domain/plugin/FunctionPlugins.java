package com.yuier.yuni.function.domain.plugin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: FunctionPlugins
 * @Author yuier
 * @Package com.yuier.yuni.function.domain.plugin
 * @Date 2024/5/6 0:19
 * @description: function 模块的全局插件管理类
 */
@Data
@AllArgsConstructor
public class FunctionPlugins {
    HashMap<String, FunctionPlugin> pluginMap;

    public FunctionPlugins() {
        pluginMap = new HashMap<>();
    }
}
