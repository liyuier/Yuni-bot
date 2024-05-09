package com.yuier.yuni.common.plugin.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: BaseDetectorPluginsDto
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.dto.function.base
 * @Date 2024/5/6 22:42
 * @description: 使用了基础消息链探测器的插件的 dto
 */
@Data
@AllArgsConstructor
public class BaseDetectorPluginsDto {
    HashMap<String, BaseDetectorPluginDto> pluginDtoMap;

    public BaseDetectorPluginsDto() {
        pluginDtoMap = new HashMap<>();
    }
}
