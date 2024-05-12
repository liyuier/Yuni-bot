package com.yuier.yuni.common.detector.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: YuniOrderArgsDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/12 1:29
 * @description: 指令参数集合 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderArgsDto implements YuniOrderSegDto{
    private ArrayList<YuniOrderArgDto> requiredArgList;
    private ArrayList<YuniOrderArgDto> optionalArgList;
}
