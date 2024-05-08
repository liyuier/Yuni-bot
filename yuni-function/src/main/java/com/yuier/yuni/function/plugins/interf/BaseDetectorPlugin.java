package com.yuier.yuni.function.plugins.interf;

import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;

/**
 * @Title: BaseDetectorPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.interf
 * @Date 2024/5/4 13:03
 * @description: 基于基础消息链处理器的插件的接口
 */
public interface BaseDetectorPlugin extends YuniPlugin {

    // 插件应用的消息探测器
    BaseDetectorDefiner detectorDefine();
}
