package com.yuier.yuni.common.detector.order.matchedout;

import lombok.AllArgsConstructor;
import lombok.Data;

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

    public OrderArgMatchedOut getArgByName(String argName) {
        return args.getArgMap().get(argName);
    }

    public Boolean argExists(String argName) {
        return args.getArgMap().containsKey(argName);
    }

    public Boolean optionExists(String optName) {
        return options.getOptionMap().containsKey(optName);
    }

    public Boolean optionArgExists(String optName, String argName) {
        return optionExists(optName) && getOptionByName(optName).argExists(argName);
    }

    public OrderOptionMatchedOut getOptionByName(String optName) {
        return options.getOptionMap().get(optName);
    }

    public OrderArgMatchedOut getOptionArgByName(String optName, String argName) {
        return options.getOptionMap().get(optName).getArgs().getArgMap().get(argName);
    }
}
