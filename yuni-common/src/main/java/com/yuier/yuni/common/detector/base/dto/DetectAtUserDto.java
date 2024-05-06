package com.yuier.yuni.common.detector.base.dto;

import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectAtUserDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base.dto
 * @Date 2024/5/6 1:40
 * @description: 探测是否 at 用户 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectAtUserDto implements BaseSubDetectorDto{
    public Boolean atUser;
    /**
     * 可以设置一个或多个具体 @ 目标
     * 当设置多个目标时，需要设置匹配模式为 “或” 还是 “与”
     */
    public ArrayList<Long> targets;
    public BaseDetectorModelEnum model;
}
