package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;

/**
 * @Title: YuniOrderSeg
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/9 22:21
 * @description: 指令段接口
 */
public interface YuniOrderSeg {

    Boolean valid();
    YuniOrderSegDto toDto();
}
