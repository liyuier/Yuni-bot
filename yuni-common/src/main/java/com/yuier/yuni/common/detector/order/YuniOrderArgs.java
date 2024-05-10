package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: YuniOrderArgs
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/11 0:49
 * @description: 指令参数列表
 */
@Data
@AllArgsConstructor
public class YuniOrderArgs implements YuniOrderSeg{

    private ArrayList<YuniOrderArg> argList;

    public YuniOrderArgs() {
        argList = new ArrayList<>();
    }

    @Override
    public Boolean valid() {
        boolean flag = true;
        for (YuniOrderArg arg : argList) {
            if (!arg.valid()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public YuniOrderSegDto toDto() {
        return null;
    }
}
