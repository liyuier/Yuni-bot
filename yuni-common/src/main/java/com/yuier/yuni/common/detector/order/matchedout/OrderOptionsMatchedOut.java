package com.yuier.yuni.common.detector.order.matchedout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Title: OrderOptionsMatchedOut
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.matchedout
 * @Date 2024/5/12 17:55
 * @description: 命中指令后被提取出来的选项集合
 */
@Data
@AllArgsConstructor
public class OrderOptionsMatchedOut {
    private HashMap<String, OrderOptionMatchedOut> optionMap;

    public OrderOptionsMatchedOut() {
        optionMap = new HashMap<>();
    }
}
