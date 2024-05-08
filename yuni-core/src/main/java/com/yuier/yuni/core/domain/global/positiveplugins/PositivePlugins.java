package com.yuier.yuni.core.domain.global.positiveplugins;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

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
    ArrayList<String> positivePluginIdList;

    public PositivePlugins() {
        positivePluginIdList = new ArrayList<>();
    }
}
