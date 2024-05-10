package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: YuniOrderOptions
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/11 1:04
 * @description: 指令选项的集合
 */
@Data
@AllArgsConstructor
public class YuniOrderOptions implements YuniOrderSeg{

    private ArrayList<YuniOrderOption> optionList;

    public YuniOrderOptions() {
        optionList = new ArrayList<>();
    }

    @Override
    public Boolean valid() {
        boolean flag = true;
        for (YuniOrderOption option : optionList) {
            if(!option.valid()) {
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
