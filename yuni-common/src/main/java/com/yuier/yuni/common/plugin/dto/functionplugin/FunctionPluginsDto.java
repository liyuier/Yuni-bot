package com.yuier.yuni.common.plugin.dto.functionplugin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: FunctionPluginsDto
 * @Author yuier
 * @Package com.yuier.yuni.function.domain.dto
 * @Date 2024/5/6 1:14
 * @description: 将插件信息传到 core 模块的 dto
 */
@Data
@AllArgsConstructor
public class FunctionPluginsDto {
    HashMap<String, FunctionPluginDto> pluginDtoMap;

    public FunctionPluginsDto() {
        this.pluginDtoMap = new HashMap<>();
    }
}
