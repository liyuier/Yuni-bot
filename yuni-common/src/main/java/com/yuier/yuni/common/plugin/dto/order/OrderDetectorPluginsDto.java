package com.yuier.yuni.common.plugin.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: OrderDetectorPluginDto
 * @Author yuier
 * @Package com.yuier.yuni.common.plugin.dto.order
 * @Date 2024/5/12 16:27
 * @description: 使用了指令消息探测器的插件 dto
 */
@Data
@AllArgsConstructor
public class OrderDetectorPluginsDto {
    HashMap<String, OrderDetectorPluginDto> pluginDtoMap;

    public OrderDetectorPluginsDto() {
        pluginDtoMap = new HashMap<>();
    }
}
