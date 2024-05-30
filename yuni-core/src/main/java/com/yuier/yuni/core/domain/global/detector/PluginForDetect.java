package com.yuier.yuni.core.domain.global.detector;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;

/**
 * @Title: PluginDetector
 * @Author yuier
 * @Package com.yuier.yuni.core.detector
 * @Date 2024/5/7 23:49
 * @description: 单个插件的探测器的接口
 */
public interface PluginForDetect {
    Boolean hitListener(MessageEvent messageEvent);

    String getPluginId();
}
