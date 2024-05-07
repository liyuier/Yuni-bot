package com.yuier.yuni.common.detector.base.dto;

import com.yuier.yuni.common.detector.MessageDetectorDefinerDto;
import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: BaseDetectorDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base.dto
 * @Date 2024/5/6 1:20
 * @description: 基础消息探测器的 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDetectorDefinerDto implements MessageDetectorDefinerDto {
    BaseDetectorModelEnum detectModel;
    DetectPrefixDto detectPrefixDto;
    DetectSuffixDto detectSuffixDto;
    DetectAtBotDto detectAtBotDto;
    DetectAtUserDto detectAtUserDto;
    DetectContainKeyWordDto detectContainKeyWordDto;
    DetectMatchRegexDto detectMatchRegexDto;
    DetectFullMatchTextDto detectFullMatchTextDto;

}
