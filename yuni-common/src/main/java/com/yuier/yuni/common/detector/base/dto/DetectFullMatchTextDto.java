package com.yuier.yuni.common.detector.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectFullMatchTextDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base.dto
 * @Date 2024/5/7 22:32
 * @description: 检测消息链是否完全匹配字符串探测器的 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectFullMatchTextDto implements BaseSubDetectorDto{
    private ArrayList<String> fullTexts;
}
