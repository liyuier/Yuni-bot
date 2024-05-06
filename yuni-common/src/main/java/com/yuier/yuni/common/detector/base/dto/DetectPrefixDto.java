package com.yuier.yuni.common.detector.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectPrefixDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base.dto
 * @Date 2024/5/6 1:42
 * @description: 检测是否包含前缀 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectPrefixDto implements BaseSubDetectorDto{
    /**
     * 可设置检测多个前缀，检测模式固定为 “或”
     * 即命中设置值之一即为命中
     */
    private ArrayList<String> prefixes;
}
