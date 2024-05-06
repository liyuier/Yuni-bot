package com.yuier.yuni.common.detector.base.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: DetectAtBotDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base.dto
 * @Date 2024/5/6 1:36
 * @description: 检测 at bot 探测器 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectAtBotDto implements BaseSubDetectorDto{
    public Boolean atBot;
}
