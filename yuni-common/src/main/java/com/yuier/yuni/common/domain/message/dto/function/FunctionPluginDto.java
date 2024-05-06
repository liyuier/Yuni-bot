package com.yuier.yuni.common.domain.message.dto.function;

import com.yuier.yuni.common.detector.MessageDetectorDefinerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: FunctionPluginDto
 * @Author yuier
 * @Package com.yuier.yuni.function.domain.dto
 * @Date 2024/5/6 1:16
 * @description: 单个插件信息的 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionPluginDto {
    String pluginId;
    MessageDetectorDefinerDto messageDetectorDefinerDto;
}
