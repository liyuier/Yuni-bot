package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
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

    /**
     * 可设置一个或多个关键字。当设置多个关键字时，需要指定匹配模式为 “或” 还是 “与”
     */
    private ArrayList<String> keyWords;
    private BaseDetectorModelEnum model;

    public DetectContainKeyWord() {
        keyWords = new ArrayList<>();
    }

    public void addKeyWord(String key) {
        keyWords.add(key);
    }

    public void addKeyWord(String key, BaseDetectorModelEnum model) {
        keyWords.add(key);
        this.model = model;
    }

    public void addKeyWord(String[] keys) {
        for (String key : keys) {
            keyWords.add(key.trim());
        }
    }

    public void addKeyWord(String[] keys, BaseDetectorModelEnum model) {
        for (String key : keys) {
            keyWords.add(key.trim());
        }
        this.model = model;
    }

    public void addKeyWord(ArrayList<String> keys) {
        keyWords.addAll(keys.stream().map(String::trim).toList());
    }

    public void addKeyWord(ArrayList<String> keys, BaseDetectorModelEnum model) {
        keyWords.addAll(keys.stream().map(String::trim).toList());
        this.model = model;
    }

    public void setDetectModel(BaseDetectorModelEnum model) {
        this.model = model;
    }

    @Override
    public Boolean valid() {
        // 未定义
        if (null == keyWords || keyWords.isEmpty()) {
            return false;
        }
        // 非法定义
        if (keyWords.size() > 1 && model == null) {
            throw new RuntimeException("DetectContainKeyWord 探测器定义多个关键字，但未设定匹配模式！");
        }
        return true;
    }
}
