package com.yuier.yuni.common.detector.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: DetectMatchRegex
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/4 4:18
 * @description: 检测消息链是否符合正则表达式
 */
@Data
@AllArgsConstructor
public class DetectMatchRegex implements BaseDetector{

    private String regex;

    public DetectMatchRegex() {
        regex = "";
    }

    public void setRegex(String reg) {
        regex = reg;
    }

    @Override
    public Boolean defined() {
        return null != regex && !regex.isEmpty();
    }
}
