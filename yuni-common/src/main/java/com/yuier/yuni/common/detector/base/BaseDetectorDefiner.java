package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.detector.MessageDetectorDefiner;
import com.yuier.yuni.common.detector.MessageDetectorDefinerDto;
import com.yuier.yuni.common.detector.base.dto.BaseDetectorDefinerDto;
import com.yuier.yuni.common.detector.base.dto.BaseSubDetectorDto;
import com.yuier.yuni.common.detector.base.dto.DetectPrefixDto;
import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import com.yuier.yuni.common.enums.MessageDetectorEnum;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Title: BaseDetector
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/3 21:17
 * @description: 基础消息链处理器
 */
@Service
@Data
public class BaseDetectorDefiner implements MessageDetectorDefiner {

    private BaseDetectorDefiner() {

    }

    /**
     * 静态方法 define 返回 detector 实例，启动链式调用
     * 在 define 后的链式调用的方法实际上都是在 builder 返回的 detector 字段在执行
     * 所有的数据都储存在该字段中
     */


    // 链式调用操作对象
    private static BaseDetectorDefiner detector;

    int conditions;
    // 前缀
    private DetectPrefix detectPrefix;
    // 后缀
    private DetectSuffix detectSuffix;
    // at 机器人
    private DetectAtBot detectAtBot;
    // at 群友
    private DetectAtUser detectAtUser;
    // 检测消息链是否包含指定关键字
    private DetectContainKeyWord detectContainKeyWord;
    // 检测消息链是否与正则表达式相匹配
    private DetectMatchRegex detectMatchRegex;
    // 检测消息链是否完全匹配字符串
    private DetectFullMatchText detectFullMatchText;
    // 检测模式
    private BaseDetectorModelEnum detectModel;

    public static BaseDetectorDefiner build() {
        detector = new BaseDetectorDefiner();
        detector.setDetectPrefix(new DetectPrefix());
        detector.setDetectSuffix(new DetectSuffix());
        detector.setDetectAtBot(new DetectAtBot());
        detector.setDetectAtUser(new DetectAtUser());
        detector.setDetectContainKeyWord(new DetectContainKeyWord());
        detector.setDetectMatchRegex(new DetectMatchRegex());
        detector.setDetectFullMatchText(new DetectFullMatchText());
        return detector;
    }

    public BaseDetectorDefiner startWith(String[] pres) {
        if (!detectPrefix.valid()) {
            conditions ++;
        }
        detectPrefix.addPrefix(pres);
        return this;
    }

    public BaseDetectorDefiner startWith(String pre) {
        if (!detectPrefix.valid()) {
            conditions ++;
        }
        detectPrefix.addPrefix(pre);
        return this;
    }

    public BaseDetectorDefiner startWith(ArrayList<String> pre) {
        if (!detectPrefix.valid()) {
            conditions ++;
        }
        detectPrefix.addPrefix(pre);
        return this;
    }

    public BaseDetectorDefiner endWith(String[] ends) {
        if (!detectSuffix.valid()) {
            conditions ++;
        }
        detectSuffix.addSuffix(ends);
        return this;
    }

    public BaseDetectorDefiner endWith(String end) {
        if (!detectSuffix.valid()) {
            conditions ++;
        }
        detectSuffix.addSuffix(end);
        return this;
    }

    public BaseDetectorDefiner endWith(ArrayList<String> end) {
        if (!detectSuffix.valid()) {
            conditions ++;
        }
        detectSuffix.addSuffix(end);
        return this;
    }

    public BaseDetectorDefiner atBot() {
        if (!detectAtBot.valid()) {
            conditions ++;
        }
        detectAtBot.setTrue();
        return this;
    }

    public BaseDetectorDefiner atSomeone() {
        if (!detectAtUser.valid()) {
            conditions ++;
        }
        detectAtUser.setTrue();
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long userId) {
        if (!detectAtUser.valid()) {
            conditions ++;
        }
        detectAtUser.addTarget(userId);
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long userId, BaseDetectorModelEnum model) {
        if (!detectAtUser.valid()) {
            conditions ++;
        }
        detectAtUser.addTarget(userId, model);
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long[] userId) {
        if (!detectAtUser.valid()) {
            conditions ++;
        }
        detectAtUser.addTarget(userId);
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long[] userId, BaseDetectorModelEnum model) {
        if (!detectAtUser.valid()) {
            conditions ++;
        }
        detectAtUser.addTarget(userId, model);
        return this;
    }

    public BaseDetectorDefiner atSomeone(ArrayList<Long> userId) {
        if (!detectAtUser.valid()) {
            conditions ++;
        }
        detectAtUser.addTarget(userId);
        return this;
    }

    public BaseDetectorDefiner atSomeone(ArrayList<Long> userId, BaseDetectorModelEnum model) {
        if (!detectAtUser.valid()) {
            conditions ++;
        }
        detectAtUser.addTarget(userId, model);
        return this;
    }

    public BaseDetectorDefiner setAtSomeoneDetectModel(BaseDetectorModelEnum model) {
        conditions ++;
        detectAtUser.setDetectModel(model);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String word) {
        if (!detectContainKeyWord.valid()) {
            conditions ++;
        }
        detectContainKeyWord.addKeyWord(word);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String word, BaseDetectorModelEnum model) {
        if (!detectContainKeyWord.valid()) {
            conditions ++;
        }
        detectContainKeyWord.addKeyWord(word, model);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String[] words) {
        if (!detectContainKeyWord.valid()) {
            conditions ++;
        }
        detectContainKeyWord.addKeyWord(words);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String[] words, BaseDetectorModelEnum model) {
        if (!detectContainKeyWord.valid()) {
            conditions ++;
        }
        detectContainKeyWord.addKeyWord(words, model);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(ArrayList<String> words) {
        if (!detectContainKeyWord.valid()) {
            conditions ++;
        }
        detectContainKeyWord.addKeyWord(words);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(ArrayList<String> words, BaseDetectorModelEnum model) {
        if (!detectContainKeyWord.valid()) {
            conditions ++;
        }
        detectContainKeyWord.addKeyWord(words, model);
        return this;
    }

    public BaseDetectorDefiner setContainKeyWordDetectModel(BaseDetectorModelEnum model) {
        conditions ++;
        detectContainKeyWord.setDetectModel(model);
        return this;
    }

    public BaseDetectorDefiner matchReg(String reg) {
        if (!detectMatchRegex.valid()) {
            conditions ++;
        }
        detectMatchRegex.setRegex(reg);
        return this;
    }

    public BaseDetectorDefiner addFullMatchText(String string) {
        if (!detectFullMatchText.valid()) {
            conditions ++;
        }
        detectFullMatchText.addFullText(string);
        return this;
    }

    public BaseDetectorDefiner addFullMatchText(String[] string) {
        if (!detectFullMatchText.valid()) {
            conditions ++;
        }
        detectFullMatchText.addFullText(string);
        return this;
    }

    public BaseDetectorDefiner addFullMatchText(ArrayList<String> string) {
        if (!detectFullMatchText.valid()) {
            conditions ++;
        }
        detectFullMatchText.addFullText(string);
        return this;
    }

    public BaseDetectorDefiner setDetectModel(BaseDetectorModelEnum model) {
        detectModel = model;
        return this;
    }

    @Override
    public MessageDetectorEnum detectorType() {
        return MessageDetectorEnum.BASE;
    }

    @Override
    public Boolean defineValid() {
        if (conditions == 0 || (conditions > 1 && detectModel == null)) {
            return false;
        }
        return true;
    }

    @Override
    public MessageDetectorDefinerDto toDto() {
        BaseDetectorDefinerDto dto = new BaseDetectorDefinerDto();
        // 设置消息链探测器
        if (detectPrefix.valid()) {
            dto.setDetectPrefixDto(detectPrefix.toDto());
        }
        if (detectSuffix.valid()) {
            dto.setDetectSuffixDto(detectSuffix.toDto());
        }
        if (detectAtBot.valid()) {
            dto.setDetectAtBotDto(detectAtBot.toDto());
        }
        if (detectAtUser.valid()) {
            dto.setDetectAtUserDto(detectAtUser.toDto());
        }
        if (detectContainKeyWord.valid()) {
            dto.setDetectContainKeyWordDto(detectContainKeyWord.toDto());
        }
        if (detectMatchRegex.valid()) {
            dto.setDetectMatchRegexDto(detectMatchRegex.toDto());
        }
        if (detectFullMatchText.valid()) {
            dto.setDetectFullMatchTextDto(detectFullMatchText.toDto());
        }
        // 设置匹配模式
        dto.setDetectModel(detectModel);
        return dto;
    }
}
