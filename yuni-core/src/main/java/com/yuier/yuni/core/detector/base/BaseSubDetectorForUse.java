package com.yuier.yuni.core.detector.base;

import com.yuier.yuni.common.domain.message.MessageChain;

/**
 * @Title: BaseSubDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:45
 * @description: 实际使用的基础消息链子探测器接口
 */
public interface BaseSubDetectorForUse {
    Boolean hit(MessageChain chain);
}
