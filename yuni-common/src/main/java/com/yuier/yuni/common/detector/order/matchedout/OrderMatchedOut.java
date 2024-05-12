package com.yuier.yuni.common.detector.order.matchedout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderMatcheOut
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.matchedout
 * @Date 2024/5/12 17:55
 * @description: 命中指令后被提取出来的指令类
 */
@Data
@AllArgsConstructor
public class OrderMatchedOut {
    private OrderArgsMatchedOut args;
    private OrderOptionsMatchedOut options;

    public OrderMatchedOut() {
        args = new OrderArgsMatchedOut();
        options = new OrderOptionsMatchedOut();
    }

    public OrderArgMatchedOut getArg(String argName) {
        return args.getArgMap().get(argName);
    }

    public OrderOptionMatchedOut getOption(String optName) {
        return options.getOptionMap().get(optName);
    }
}
