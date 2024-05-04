package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Title: DetectAtUser
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/4 4:02
 * @description: @ 事件探测器
 */
@Data
@AllArgsConstructor
public class DetectAtUser implements BaseDetector{

    public Boolean atUser;
    /**
     * 可以设置一个或多个具体 @ 目标
     * 当设置多个目标时，需要设置匹配模式为 “或” 还是 “与”
     */
    public ArrayList<Long> targets;
    public BaseDetectorModelEnum model;

    public DetectAtUser() {
        atUser = false;
    }

    public void setTrue() {
        atUser = true;
    }

    public void addTarget(Long userId) {
        setTrue();
        targets.add(userId);
    }

    public void addTarget(Long userId, BaseDetectorModelEnum model) {
        setTrue();
        targets.add(userId);
        this.model = model;
    }

    public void addTarget(Long[] userIds) {
        setTrue();
        targets.addAll(Arrays.asList(userIds));
    }

    public void addTarget(Long[] userIds, BaseDetectorModelEnum model) {
        setTrue();
        targets.addAll(Arrays.asList(userIds));
        this.model = model;
    }

    public void addTarget(ArrayList<Long> userIds) {
        targets.addAll(userIds);
    }

    public void addTarget(ArrayList<Long> userIds, BaseDetectorModelEnum model) {
        targets.addAll(userIds);
        this.model = model;
    }

    public void setDetectModel(BaseDetectorModelEnum model) {
        this.model = model;
    }

    @Override
    public Boolean valid() {
        // 未定义
        if (!atUser) {
            return false;
        }
        // 非法定义
        if (null != targets && targets.size() > 1 && null == model) {
            throw new RuntimeException("DetectAtUser 设置多个目标用户，但未设定匹配模式！");
        }
        return true;
    }
}
