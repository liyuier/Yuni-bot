package com.yuier.yuni.common.plugin;

import com.yuier.yuni.common.detector.MessageDetectorDefiner;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.listener.MessageTypeListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @Title: FunctionPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.domain.plugin
 * @Date 2024/5/6 0:31
 * @description: function 模块插件类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionPlugin {
    // 插件 ID
    private String pluginId;
    // 插件文件产生的 Bean
    private Object pluginBean;
    // 插件监听的消息类型
    private MessageTypeListener listener;
    // 插件的消息探测器
    private MessageDetectorDefiner detectorDefiner;
    // 插件的描述
    private String description;
    // 插件的入口
    private Method runMethod;

    public Boolean isPositive() {
        return null == detectorDefiner;
    }

    public Boolean useDetector(Class<?> detectorClass) {
        return detectorClass.isInstance(detectorDefiner);
    }
}
