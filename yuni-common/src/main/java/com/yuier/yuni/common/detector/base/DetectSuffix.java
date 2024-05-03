package com.yuier.yuni.common.detector.base;

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
public class DetectSuffix implements BaseDetector{

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

    @Override
    public Boolean defined() {
        return null != suffixes && !suffixes.isEmpty();
    }

}
