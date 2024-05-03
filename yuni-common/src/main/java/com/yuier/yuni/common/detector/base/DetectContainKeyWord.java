package com.yuier.yuni.common.detector.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: DetectContainKeyWord
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/4 4:11
 * @description: 关键词探测器
 */
@Data
@AllArgsConstructor
public class DetectContainKeyWord implements BaseDetector{

    private ArrayList<String> keyWords;

    public DetectContainKeyWord() {
        keyWords = new ArrayList<>();
    }

    public void addKeyWord(String key) {
        keyWords.add(key);
    }

    public void addKeyWord(String[] keys) {
        for (String key : keys) {
            keyWords.add(key.trim());
        }
    }

    @Override
    public Boolean defined() {
        return null != keyWords && !keyWords.isEmpty();
    }
}
