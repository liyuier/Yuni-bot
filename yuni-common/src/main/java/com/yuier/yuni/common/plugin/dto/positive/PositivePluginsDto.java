package com.yuier.yuni.common.plugin.dto.positive;

import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.plugin.dto.base.BaseDetectorPluginDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Title: PositivePluginsDto
 * @Author yuier
 * @Package com.yuier.yuni.common.plugin.dto.functionplugin.positive
 * @Date 2024/5/8 22:28
 * @description: 主动触发的插件集合 DTO
 */
@Data
@AllArgsConstructor
public class PositivePluginsDto {
    YuniModuleEnum module;
    ArrayList<String> positivePluginIdList;
    HashMap<String, PositivePluginDto> pluginDtoMap;

    public PositivePluginsDto() {
        this.pluginDtoMap = new HashMap<>();
    }

    public PositivePluginsDto(YuniModuleEnum module) {
        this();
        this.module = module;
    }
}
