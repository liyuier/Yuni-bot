package com.yuier.yuni.common.plugin.dto.function.positive;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

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
    ArrayList<String> positivePluginIdList;

    public PositivePluginsDto() {
        this.positivePluginIdList = new ArrayList<>();
    }
}
