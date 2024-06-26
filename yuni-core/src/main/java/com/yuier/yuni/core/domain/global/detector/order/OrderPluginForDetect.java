package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.dto.YuniOrderDefinerDto;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageChainForOrder;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import com.yuier.yuni.common.plugin.dto.order.OrderDetectorPluginDto;
import com.yuier.yuni.core.domain.global.detector.PluginForDetect;
import com.yuier.yuni.core.domain.global.detector.listener.MessageTypeListenerForUse;
import lombok.Getter;

/**
 * @Title: OrderPluginForDetect
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 21:48
 * @description: 采用了指令探测器的单个插件的探测器
 */
@Getter
public class OrderPluginForDetect implements PluginForDetect {
    YuniModuleEnum module;
    String pluginId;
    MessageTypeListenerForUse listener;
    OrderDetectorForUse detector;
    String description;

    public OrderPluginForDetect(OrderDetectorPluginDto dto) {
        module = dto.getModule();
        pluginId = dto.getPluginId();
        listener = new MessageTypeListenerForUse(dto.getListenerDto());
        detector = new OrderDetectorForUse(dto.getMessageDetectorDefinerDto());
        description = dto.getDescription();
    }

    public OrderPluginForDetect(FunctionPlugin pluginBean, YuniModuleEnum module) {
        this.module = module;
        this.pluginId = pluginBean.getPluginId();
        this.listener = new MessageTypeListenerForUse(pluginBean.getListener().getListenMessageType());
        this.detector = new OrderDetectorForUse((YuniOrderDefiner) pluginBean.getDetectorDefiner());
        this.description = pluginBean.getDescription();
    }


    @Override
    public Boolean hitListener(MessageEvent messageEvent) {
        return listener.hit(messageEvent);
    }

    public Boolean hitDetector(MessageChainForOrder chainForOrder, OrderMatchedOut orderMatchedOut, String orderMark) {
        return detector.hit(chainForOrder, orderMatchedOut, orderMark);
    }
}
