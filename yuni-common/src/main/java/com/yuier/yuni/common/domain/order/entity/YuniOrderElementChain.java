package com.yuier.yuni.common.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: YuniOrderElementChain
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.order
 * @Date 2024/4/21 17:56
 * @description: 命令元素链实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderElementChain {

    ArrayList<YuniOrderElement> content;
}
