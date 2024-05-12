package com.yuier.yuni.common.detector.order.matchedout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Title: OrderArgsMatchedOut
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.arg
 * @Date 2024/5/12 17:52
 * @description: 指令中被提取出的参数列表
 */
@Data
@AllArgsConstructor
public class OrderArgsMatchedOut {
    private HashMap<String, OrderArgMatchedOut> argMap;

    public OrderArgsMatchedOut() {
        argMap = new HashMap<>();
    }
}
