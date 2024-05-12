package com.yuier.yuni.common.plugin.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @Title: OrderDetectorPlugins
 * @Author yuier
 * @Package com.yuier.yuni.common.plugin.order
 * @Date 2024/5/12 16:24
 * @description: 使用了指令消息探测器的插件集合
 */
@Data
@AllArgsConstructor
public class OrderDetectorPlugins {
    HashMap<String, OrderDetectorPlugin> pluginMap;

    public OrderDetectorPlugins() {
        this.pluginMap = new HashMap<>();
    }
}
