package com.yuier.yuni.common.detector.order.dto;

import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgRequireTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: YuniOrderArgDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/11 23:08
 * @description: 指令参数 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderArgDto implements YuniOrderSegDto{
    private String name;
    private YuniOrderArgRequireTypeEnum requireType;
    private YuniOrderArgContentTypeEnum contentType;
}
