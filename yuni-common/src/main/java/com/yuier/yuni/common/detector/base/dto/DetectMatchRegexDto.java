package com.yuier.yuni.common.detector.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: DetectMatchRegexDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base.dto
 * @Date 2024/5/6 1:41
 * @description: 探测是否符合正则表达式 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectMatchRegexDto implements BaseSubDetectorDto{
    private String regex;
}
