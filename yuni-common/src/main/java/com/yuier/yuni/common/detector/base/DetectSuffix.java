package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.detector.base.dto.BaseSubDetectorDto;
import com.yuier.yuni.common.detector.base.dto.DetectSuffixDto;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: DetectSuffix
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/4 3:50
 * @description: 消息链后缀探测器
 */
@Data
@AllArgsConstructor
public class DetectSuffix implements BaseSubDetector {

    /**
     * 可设置检测多个后缀，检测模式固定为 “或”
     * 即命中设置值之一即为命中
     */
    private ArrayList<String> suffixes;

    public DetectSuffix() {
        suffixes = new ArrayList<>();
    }

    public void addSuffix(String suf) {
        suffixes.add(suf.trim());
    }

    public void addSuffix(String[] suffes) {
        for (String suf : suffes) {
            suffixes.add(suf.trim());
        }
    }

    public void addSuffix(ArrayList<String> suffes) {
        suffixes.addAll(suffes.stream().map(String::trim).toList());
    }

    @Override
    public Boolean valid() {
        return null != suffixes && !suffixes.isEmpty();
    }

    @Override
    public DetectSuffixDto toDto() {
        return BeanCopyUtils.copyBean(this, DetectSuffixDto.class);
    }

}
