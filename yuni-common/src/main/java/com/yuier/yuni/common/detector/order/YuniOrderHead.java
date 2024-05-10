package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: YuniOrderHead
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/9 22:23
 * @description: 指令标识符，即指令的第一个字段
 */
@Data
@AllArgsConstructor
public class YuniOrderHead implements YuniOrderSeg{
    // 指令头
    private String name;

    public YuniOrderHead() {
        name = "";
    }

    @Override
    public Boolean valid() {
        return !name.isEmpty();
    }

    @Override
    public YuniOrderSegDto toDto() {
        return null;
    }
}
