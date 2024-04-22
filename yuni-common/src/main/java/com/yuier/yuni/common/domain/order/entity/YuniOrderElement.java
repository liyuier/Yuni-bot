package com.yuier.yuni.common.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: YuniOrderElement
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.order
 * @Date 2024/4/21 17:54
 * @description: 命令元素实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderElement implements YuniOrderBaseSeg{

    private String rawStr;

    @Override
    public String toString() {
        return rawStr;
    }
}
