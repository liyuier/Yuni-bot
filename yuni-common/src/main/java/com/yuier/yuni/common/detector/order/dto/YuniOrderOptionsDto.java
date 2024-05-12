package com.yuier.yuni.common.detector.order.dto;

import com.yuier.yuni.common.detector.order.YuniOrderOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: YuniOrderOptionsDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/12 16:10
 * @description: 指令选项集合 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderOptionsDto implements YuniOrderSegDto{
    private ArrayList<YuniOrderOptionDto> optionList;
}
