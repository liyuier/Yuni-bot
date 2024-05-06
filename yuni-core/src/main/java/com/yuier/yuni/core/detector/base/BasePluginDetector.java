package com.yuier.yuni.core.detector.base;

import com.yuier.yuni.common.domain.dto.functionplugin.base.BaseDetectorPluginDto;
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
public class BasePluginDetector {
    String pluginId;
    BaseDetectorForUse detector;

    public BasePluginDetector(BaseDetectorPluginDto dto) {
        pluginId = dto.getPluginId();
        detector = new BaseDetectorForUse(dto.getMessageDetectorDefinerDto());
    }
}
