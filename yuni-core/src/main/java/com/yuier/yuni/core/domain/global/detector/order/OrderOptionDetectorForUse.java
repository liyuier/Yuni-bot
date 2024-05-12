package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderOptionDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 23:01
 * @description: 实际使用的选项探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOptionDetectorForUse {
    private String name;
    private String flag;
    private OrderArgsDetectorForUse optionArgs;

    public OrderOptionDetectorForUse(YuniOrderOptionDto dto) {
        name = dto.getName();
        flag = dto.getFlag();
        optionArgs = new OrderArgsDetectorForUse(dto.getOptionArgs());
    }
}
