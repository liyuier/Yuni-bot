package com.yuier.yuni.function.plugins;

import com.yuier.yuni.function.plugins.YuniPlugin;

/**
 * @Title: YuniPositivePlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.positive
 * @Date 2024/4/16 20:23
 * @description: 主动生效的功能插件接口
 */
public interface YuniPositivePlugin extends YuniPlugin {
    void run();
}
