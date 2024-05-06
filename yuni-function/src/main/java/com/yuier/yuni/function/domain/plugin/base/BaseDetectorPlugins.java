package com.yuier.yuni.function.domain.plugin.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: BaseDetectorPlugins
 * @Author yuier
 * @Package com.yuier.yuni.function.domain.plugin.base
 * @Date 2024/5/6 22:30
 * @description: 使用基础消息链探测器的插件集合
 */
@Data
@AllArgsConstructor
public class BaseDetectorPlugins {

    HashMap<String, BaseDetectorPlugin> pluginMap;

    public BaseDetectorPlugins() {
        this.pluginMap = new HashMap<>();
    }
}
