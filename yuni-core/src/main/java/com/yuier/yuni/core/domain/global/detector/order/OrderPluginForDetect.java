package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.listener.dto.MessageTypeListenerDto;
import com.yuier.yuni.common.plugin.dto.order.OrderDetectorPluginDto;
import com.yuier.yuni.core.domain.global.detector.PluginForDetect;
import com.yuier.yuni.core.domain.global.detector.listener.MessageTypeListenerForUse;

/**
 * @Title: OrderPluginForDetect
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 21:48
 * @description: 采用了指令探测器的单个插件的探测器
 */
public class OrderPluginForDetect implements PluginForDetect {
    String pluginId;
    MessageTypeListenerForUse listener;
    OrderDetectorForUse detector;

    public OrderPluginForDetect(OrderDetectorPluginDto dto) {
        pluginId = dto.getPluginId();
        listener = new MessageTypeListenerForUse(dto.getListenerDto());
        detector = new OrderDetectorForUse(dto.getMessageDetectorDefinerDto());
    }


    @Override
    public Boolean hitListener(MessageEvent messageEvent) {
        return false;
    }

    @Override
    public Boolean hitDetector(MessageChain chain) {
        return false;
    }
}
