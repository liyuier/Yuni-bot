package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderHeadDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderHeadDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 21:52
 * @description: 实际使用的指令头探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHeadDetectorForUse {
    private String name;

    public OrderHeadDetectorForUse(YuniOrderHeadDto dto) {
        name = dto.getName();
    }
}
