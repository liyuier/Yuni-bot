package com.yuier.yuni.common.detector;

import com.yuier.yuni.common.enums.MessageDetectorEnum;

/**
 * @Title: MessageDetectorDefiner
 * @Author yuier
 * @Package com.yuier.yuni.common.detector
 * @Date 2024/5/5 19:48
 * @description: 消息链探测器接口
 */
public interface MessageDetectorDefiner {

    MessageDetectorEnum detectorType();
    Boolean defineValid();
}
