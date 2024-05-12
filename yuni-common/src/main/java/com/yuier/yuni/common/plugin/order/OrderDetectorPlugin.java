package com.yuier.yuni.common.plugin.order;

import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @Title: OrderDetectorPlugin
 * @Author yuier
 * @Package com.yuier.yuni.common.plugin.order
 * @Date 2024/5/12 16:24
 * @description: 使用了指令消息探测器的插件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetectorPlugin {
    // 插件 ID
    private String pluginId;
    // 插件文件产生的 Bean
    private Object pluginBean;
    // 插件监听的消息类型
    private MessageTypeEnum listener;
    // 插件的消息探测器
    private YuniOrderDefiner detectorDefiner;
    // 插件的描述
    private String description;
    // 插件的入口
    private Method runMethod;
}
