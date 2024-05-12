package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderArgsDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderDefinerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 21:50
 * @description: 实际使用中的指令探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetectorForUse {
    private OrderHeadDetectorForUse head;
    private OrderArgsDetectorForUse args;
    private OrderOptionsDetectorForUse options;

    public OrderDetectorForUse(YuniOrderDefinerDto dto) {
        head = new OrderHeadDetectorForUse(dto.getHead());
        args = new OrderArgsDetectorForUse(dto.getArgs());
        options = new OrderOptionsDetectorForUse(dto.getOptions());
    }
}
