package com.yuier.yuni.common.detector.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: YuniOrderHeadDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/11 23:08
 * @description: 指令头 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderHeadDto implements YuniOrderSegDto{
    private String name;
}
