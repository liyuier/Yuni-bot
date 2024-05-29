package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import com.yuier.yuni.common.plugin.dto.base.BaseDetectorPluginDto;
import com.yuier.yuni.core.domain.global.detector.PluginForDetect;
import com.yuier.yuni.core.domain.global.detector.listener.MessageTypeListenerForUse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: BasePluginDetector
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:35
 * @description: 采用了基础消息链处理器的单个插件的探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePluginForDetect implements PluginForDetect {
    YuniModuleEnum module;
    String pluginId;
    MessageTypeListenerForUse listener;
    BaseDetectorForUse detector;
    String description;

    public BasePluginForDetect(BaseDetectorPluginDto dto) {
        module = dto.getModule();
        pluginId = dto.getPluginId();
        listener = new MessageTypeListenerForUse(dto.getListenerDto());
        detector = new BaseDetectorForUse(dto.getMessageDetectorDefinerDto());
        description = dto.getDescription();
    }

    public BasePluginForDetect(FunctionPlugin pluginBean, YuniModuleEnum module) {
        this.module = module;
        this.pluginId = pluginBean.getPluginId();
        this.listener = new MessageTypeListenerForUse(pluginBean.getListener().getListenMessageType());
        this.detector = new BaseDetectorForUse((BaseDetectorDefiner) pluginBean.getDetectorDefiner());
        this.description = pluginBean.getDescription();
    }

    @Override
    public Boolean hitListener(MessageEvent messageEvent) {
        return listener.hit(messageEvent);
    }

    public Boolean hitDetector(MessageChain chain) {
        return detector.hit(chain);
    }
}
