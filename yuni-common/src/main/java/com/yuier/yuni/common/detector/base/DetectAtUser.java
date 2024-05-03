package com.yuier.yuni.common.detector.base;

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
    public ArrayList<Long> targets;

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

    public void addTarget(Long[] userIds) {
        setTrue();
        targets.addAll(Arrays.asList(userIds));
    }

    @Override
    public Boolean defined() {
        return !atUser;
    }
}
