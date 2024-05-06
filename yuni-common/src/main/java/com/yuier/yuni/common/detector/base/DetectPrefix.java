package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.detector.base.dto.BaseSubDetectorDto;
import com.yuier.yuni.common.detector.base.dto.DetectPrefixDto;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: DetectPrefix
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/4 3:38
 * @description: 探测消息前缀
 */
@Data
@AllArgsConstructor
public class DetectPrefix implements BaseSubDetector {

    /**
     * 可设置检测多个前缀，检测模式固定为 “或”
     * 即命中设置值之一即为命中
     */
    private ArrayList<String> prefixes;

    public DetectPrefix() {
        prefixes = new ArrayList<>();
    }

    public void addPrefix(String pre) {
        prefixes.add(pre.trim());
    }

    public void addPrefix(String[] pres) {
        for (String pre : pres) {
            prefixes.add(pre.trim());
        }
    }

    public void addPrefix(ArrayList<String> pres) {
        prefixes.addAll(pres.stream().map(String::trim).toList());
    }

    @Override
    public Boolean valid() {
        return null != prefixes && !prefixes.isEmpty();
    }

    @Override
    public DetectPrefixDto toDto() {
        return BeanCopyUtils.copyBean(this, DetectPrefixDto.class);
    }
}
