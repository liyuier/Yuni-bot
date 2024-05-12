package com.yuier.yuni.common.detector.order.dto;

import com.yuier.yuni.common.detector.order.YuniOrderArgs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: YuniOrderOptionDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/12 1:30
 * @description: 指令选项 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderOptionDto implements YuniOrderSegDto{
    private String name;
    private String flag;
    private YuniOrderArgs optionArgs;
    private String helpInfo;
}
