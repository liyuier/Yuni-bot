package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.detector.base.dto.DetectFullMatchTextDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: DetectFullMatchText
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/7 22:30
 * @description: 探测消息链是否完全匹配字符串
 */
@Data
@AllArgsConstructor
public class DetectFullMatchText implements BaseSubDetector{

    private ArrayList<String> fullTexts;

    public DetectFullMatchText() {
        fullTexts = new ArrayList<>();
    }

    public void addFullText(String text) {
        fullTexts.add(text);
    }

    public void addFullText(String[] texts) {
        for (String text : texts) {
            fullTexts.add(text.trim());
        }
    }

    public void addFullText(ArrayList<String> texts) {
        fullTexts.addAll(texts.stream().map(String::trim).toList());
    }

    @Override
    public Boolean valid() {
        return null != fullTexts && !fullTexts.isEmpty();
    }

    @Override
    public DetectFullMatchTextDto toDto() {
        return new DetectFullMatchTextDto(fullTexts);
    }
}
