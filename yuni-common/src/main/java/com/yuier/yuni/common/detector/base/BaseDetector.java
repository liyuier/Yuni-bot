package com.yuier.yuni.common.detector.base;

/**
 * @Title: BaseDetector
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/4 3:41
 * @description: 基础消息链处理器接口
 */
public interface BaseDetector {

    // 本消息链是否被定义过
    Boolean valid();
}
