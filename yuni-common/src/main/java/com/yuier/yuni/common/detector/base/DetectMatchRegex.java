package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.detector.base.dto.BaseSubDetectorDto;
import com.yuier.yuni.common.detector.base.dto.DetectMatchRegexDto;
import com.yuier.yuni.common.utils.BeanCopyUtils;
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
public class DetectMatchRegex implements BaseSubDetector {

    private String regex;

    public DetectMatchRegex() {
        regex = "";
    }

    public void setRegex(String reg) {
        regex = reg;
    }

    @Override
    public Boolean valid() {
        return null != regex && !regex.isEmpty();
    }

    @Override
    public DetectMatchRegexDto toDto() {
        return BeanCopyUtils.copyBean(this, DetectMatchRegexDto.class);
    }
}
