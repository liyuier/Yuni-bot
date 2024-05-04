package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
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
public class BaseDetectorDefiner {

    private BaseDetectorDefiner() {

    }

    /**
     * 静态方法 define 返回 detector 实例，启动链式调用
     * 在 define 后的链式调用的方法实际上都是在 builder 返回的 detector 字段在执行
     * 所有的数据都储存在该字段中
     */


    // 链式调用操作对象
    private static BaseDetectorDefiner detector;

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
    // 检测模式
    private BaseDetectorModelEnum detectModel;

    public static BaseDetectorDefiner define() {
        detector = new BaseDetectorDefiner();
        detector.setDetectPrefix(new DetectPrefix());
        detector.setDetectSuffix(new DetectSuffix());
        detector.setDetectAtBot(new DetectAtBot());
        detector.setDetectAtUser(new DetectAtUser());
        detector.setDetectContainKeyWord(new DetectContainKeyWord());
        detector.setDetectMatchRegex(new DetectMatchRegex());
        return detector;
    }

    public BaseDetectorDefiner startWith(String[] pres) {
        detectPrefix.addPrefix(pres);
        return this;
    }

    public BaseDetectorDefiner startWith(String pre) {
        detectPrefix.addPrefix(pre);
        return this;
    }

    public BaseDetectorDefiner startWith(ArrayList<String> pre) {
        detectPrefix.addPrefix(pre);
        return this;
    }

    public BaseDetectorDefiner endWith(String[] ends) {
        detectSuffix.addSuffix(ends);
        return this;
    }

    public BaseDetectorDefiner endWith(String end) {
        detectSuffix.addSuffix(end);
        return this;
    }

    public BaseDetectorDefiner endWith(ArrayList<String> end) {
        detectSuffix.addSuffix(end);
        return this;
    }

    public BaseDetectorDefiner atBot() {
        detectAtBot.setTrue();
        return this;
    }

    public BaseDetectorDefiner atSomeone() {
        detectAtUser.setTrue();
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long userId) {
        detectAtUser.addTarget(userId);
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long userId, BaseDetectorModelEnum model) {
        detectAtUser.addTarget(userId, model);
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long[] userId) {
        detectAtUser.addTarget(userId);
        return this;
    }

    public BaseDetectorDefiner atSomeone(Long[] userId, BaseDetectorModelEnum model) {
        detectAtUser.addTarget(userId, model);
        return this;
    }

    public BaseDetectorDefiner atSomeone(ArrayList<Long> userId) {
        detectAtUser.addTarget(userId);
        return this;
    }

    public BaseDetectorDefiner atSomeone(ArrayList<Long> userId, BaseDetectorModelEnum model) {
        detectAtUser.addTarget(userId, model);
        return this;
    }

    public BaseDetectorDefiner setAtSomeoneDetectModel(BaseDetectorModelEnum model) {
        detectAtUser.setDetectModel(model);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String word) {
        detectContainKeyWord.addKeyWord(word);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String word, BaseDetectorModelEnum model) {
        detectContainKeyWord.addKeyWord(word, model);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String[] words) {
        detectContainKeyWord.addKeyWord(words);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(String[] words, BaseDetectorModelEnum model) {
        detectContainKeyWord.addKeyWord(words, model);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(ArrayList<String> words) {
        detectContainKeyWord.addKeyWord(words);
        return this;
    }

    public BaseDetectorDefiner containKeyWord(ArrayList<String> words, BaseDetectorModelEnum model) {
        detectContainKeyWord.addKeyWord(words, model);
        return this;
    }

    public BaseDetectorDefiner setContainKeyWordDetectModel(BaseDetectorModelEnum model) {
        detectContainKeyWord.setDetectModel(model);
        return this;
    }

    public BaseDetectorDefiner matchReg(String reg) {
        detectMatchRegex.setRegex(reg);
        return this;
    }

    public BaseDetectorDefiner setDetectModel(BaseDetectorModelEnum model) {
        detectModel = model;
        return this;
    }
}
