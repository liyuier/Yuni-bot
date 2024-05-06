package com.yuier.yuni.common.detector.base.dto;

import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectContainKeyWordDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base.dto
 * @Date 2024/5/6 1:41
 * @description: 探测是否包含关键字 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectContainKeyWordDto implements BaseSubDetectorDto{
    private ArrayList<String> keyWords;
    private BaseDetectorModelEnum model;
}
