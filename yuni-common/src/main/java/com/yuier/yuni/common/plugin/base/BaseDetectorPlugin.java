package com.yuier.yuni.common.plugin.base;

import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @Title: BaseDetectorPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.domain.plugin.base
 * @Date 2024/5/6 22:29
 * @description: 使用基础消息链探测器的插件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDetectorPlugin {
    // 插件 ID
    private String pluginId;
    // 插件文件产生的 Bean
    private Object pluginBean;
    // 插件监听的消息类型
    private MessageTypeEnum listener;
    // 插件的消息探测器
    private BaseDetectorDefiner detectorDefiner;
    // 插件的描述
    private String description;
    // 插件的入口
    private Method runMethod;
}
