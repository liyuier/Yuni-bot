package com.yuier.yuni.common.detector.base;

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
public class DetectPrefix implements BaseDetector{

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

    @Override
    public Boolean defined() {
        return null != prefixes && !prefixes.isEmpty();
    }
}
