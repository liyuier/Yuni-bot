package com.yuier.yuni.core.detector.base;

import com.yuier.yuni.common.plugin.dto.functionplugin.base.BaseDetectorPluginDto;
import com.yuier.yuni.common.plugin.dto.functionplugin.base.BaseDetectorPluginsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: BasePluginsDetector
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:34
 * @description: 采用了基础消息链处理器的插件的消息探测集合
 */
@Data
@AllArgsConstructor
public class BasePluginsDetector {
    HashMap<String, BasePluginDetector> pluginDtoHashMap;

    public BasePluginsDetector() {
        pluginDtoHashMap = new HashMap<>();
    }

    public BasePluginsDetector(BaseDetectorPluginsDto dto) {
        pluginDtoHashMap = new HashMap<>();
        HashMap<String, BaseDetectorPluginDto> pluginDtoMap = dto.getPluginDtoMap();
        for (String pluginId : pluginDtoMap.keySet()) {
            pluginDtoHashMap.put(
                    pluginId,
                    new BasePluginDetector(pluginDtoMap.get(pluginId))
            );
        }
    }
}
