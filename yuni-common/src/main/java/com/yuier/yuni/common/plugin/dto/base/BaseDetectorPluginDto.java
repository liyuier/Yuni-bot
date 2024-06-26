package com.yuier.yuni.common.plugin.dto.base;

import com.yuier.yuni.common.detector.base.dto.BaseDetectorDefinerDto;
import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.listener.dto.MessageTypeListenerDto;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: BaseDetectorPluginDto
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.dto.function.base
 * @Date 2024/5/6 22:42
 * @description: 使用了基础消息链探测器的单个插件的 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDetectorPluginDto {
    YuniModuleEnum module;
    String pluginId;
    MessageTypeListenerDto listenerDto;
    BaseDetectorDefinerDto messageDetectorDefinerDto;
    String description;

    public BaseDetectorPluginDto(FunctionPlugin plugin) {
        pluginId = plugin.getPluginId();
        listenerDto = new MessageTypeListenerDto(plugin.getListener());
        messageDetectorDefinerDto = (BaseDetectorDefinerDto) plugin.getDetectorDefiner().toDto();
        description = plugin.getDescription();
    }

    public BaseDetectorPluginDto(FunctionPlugin plugin, YuniModuleEnum module) {
        this(plugin);
        this.module = module;
    }
}
