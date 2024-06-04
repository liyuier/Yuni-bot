package com.yuier.yuni.core.domain.global.positiveplugins;

import com.yuier.yuni.common.plugin.dto.positive.PositivePluginDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Title: PositivePlugins
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global
 * @Date 2024/5/8 22:50
 * @description: 主动触发的插件集合
 */
@Data
@AllArgsConstructor
public class PositivePlugins {
    /**
     * 键为模块名
     * 值为插件名列表
     */
    HashMap<String, PositivePluginDto> pluginMap;

    public PositivePlugins() {
        pluginMap = new HashMap<>();
    }
}
