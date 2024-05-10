package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: YuniOrderOption
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/9 22:25
 * @description: 指令选项字段
 */
@Data
@AllArgsConstructor
public class YuniOrderOption implements YuniOrderSeg{

    // 选项的名称
    private String name;

    // 选项的标识
    private String flag;

    // 选项携带的参数
    private YuniOrderArgs optionArgs;

    // 帮助信息
    private String helpInfo;

    public YuniOrderOption() {
        optionArgs = new YuniOrderArgs();
    }

    @Override
    public Boolean valid() {
        return null != name && null != flag && optionArgs.valid();
    }

    @Override
    public YuniOrderSegDto toDto() {
        return null;
    }
}
