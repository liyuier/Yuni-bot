package com.yuier.yuni.core.detector;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;

/**
 * @Title: YuniMessageDetector
 * @Author yuier
 * @Package com.yuier.yuni.core.detector
 * @Date 2024/4/16 22:54
 * @description: YuniMessageDetector
 */
public interface YuniMessageDetector {
    boolean detect(MessageEvent messageEvent);
}
