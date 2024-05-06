package com.yuier.yuni.common.domain.dto.functionplugin.base;

import com.yuier.yuni.common.detector.base.dto.BaseDetectorDefinerDto;
import com.yuier.yuni.common.domain.dto.functionplugin.FunctionPluginDto;
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
    String pluginId;
    BaseDetectorDefinerDto messageDetectorDefinerDto;

    public BaseDetectorPluginDto(FunctionPluginDto functionPluginDto) {
        pluginId = functionPluginDto.getPluginId();
        messageDetectorDefinerDto = (BaseDetectorDefinerDto) functionPluginDto.getMessageDetectorDefinerDto();
    }
}
