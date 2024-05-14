package com.yuier.yuni.common.plugin.dto.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderDefinerDto;
import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.listener.dto.MessageTypeListenerDto;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderDetectorPluginsDto
 * @Author yuier
 * @Package com.yuier.yuni.common.plugin.dto.order
 * @Date 2024/5/12 16:28
 * @description: 使用了指令消息探测器的插件集合 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetectorPluginDto {
    YuniModuleEnum module;
    String pluginId;
    MessageTypeListenerDto listenerDto;
    YuniOrderDefinerDto messageDetectorDefinerDto;

    public OrderDetectorPluginDto(FunctionPlugin plugin) {
        pluginId = plugin.getPluginId();
        listenerDto = new MessageTypeListenerDto(plugin.getListener());
        messageDetectorDefinerDto = (YuniOrderDefinerDto) plugin.getDetectorDefiner().toDto();
    }

    public OrderDetectorPluginDto(FunctionPlugin plugin, YuniModuleEnum module) {
        this(plugin);
        this.module = module;
    }
}
