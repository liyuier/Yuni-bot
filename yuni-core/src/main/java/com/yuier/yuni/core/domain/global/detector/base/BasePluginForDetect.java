package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
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
    String pluginId;
    MessageTypeListenerForUse listener;
    BaseDetectorForUse detector;

    public BasePluginForDetect(BaseDetectorPluginDto dto) {
        pluginId = dto.getPluginId();
        listener = new MessageTypeListenerForUse(dto.getListenerDto());
        detector = new BaseDetectorForUse(dto.getMessageDetectorDefinerDto());
    }

    @Override
    public Boolean hitListener(MessageEvent messageEvent) {
        return listener.hit(messageEvent);
    }

    @Override
    public Boolean hitDetector(MessageChain chain) {
        return detector.hit(chain);
    }
}