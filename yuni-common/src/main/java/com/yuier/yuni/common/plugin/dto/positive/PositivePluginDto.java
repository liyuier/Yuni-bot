package com.yuier.yuni.common.plugin.dto.positive;

import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: PositivePluginDto
 * @Author yuier
 * @Package com.yuier.yuni.common.plugin.dto.positive
 * @Date 2024/6/4 23:56
 * @description: 主动触发的单个插件 Dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositivePluginDto {
    YuniModuleEnum module;
    String pluginId;
    String description;

    public PositivePluginDto(FunctionPlugin functionPlugin) {
        pluginId = functionPlugin.getPluginId();
        description = functionPlugin.getDescription();
    }

    public PositivePluginDto(FunctionPlugin functionPlugin, YuniModuleEnum module) {
        this(functionPlugin);
        this.module = module;
    }
}
