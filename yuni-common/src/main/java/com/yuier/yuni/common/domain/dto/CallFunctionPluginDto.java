package com.yuier.yuni.common.domain.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: CallFunctionPluginDto
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.dto
 * @Date 2024/5/7 0:43
 * @description: 命中探测器后，调用 function 模块使用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallFunctionPluginDto {
    private String pluginId;
    JsonNode jsonNode;
}
