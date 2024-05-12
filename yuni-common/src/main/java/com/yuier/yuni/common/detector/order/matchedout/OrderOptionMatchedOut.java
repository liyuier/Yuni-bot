package com.yuier.yuni.common.detector.order.matchedout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderOptionMatchedOut
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.matchedout
 * @Date 2024/5/12 17:53
 * @description: 指令中被提取出来的选项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOptionMatchedOut {
    private String name;
    private OrderArgsMatchedOut args;
    private String helpInfo;
}
