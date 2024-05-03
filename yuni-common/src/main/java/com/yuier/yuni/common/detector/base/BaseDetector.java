package com.yuier.yuni.common.detector.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class BaseDetector {

    // 链式调用操作对象
    private static BaseDetector detector;

    // 前缀
    private ArrayList<String> prefix;
    // 后缀
    private ArrayList<String> suffix;
    // at
    private Boolean atBot;
    //
}
