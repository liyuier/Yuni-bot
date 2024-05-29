package com.yuier.yuni.core.domain.global.detector;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: PluginsDetector
 * @Author yuier
 * @Package com.yuier.yuni.core.detector
 * @Date 2024/5/7 23:48
 * @description: 插件探测器集合
 */
@Data
@AllArgsConstructor
public class PluginsForDetect {
    /**
     * 键 插件名称
     * 值 重建后的插件实例
     */
    HashMap<String, PluginForDetect> pluginMap;

    public PluginsForDetect() {
        pluginMap = new HashMap<>();
    }
}
