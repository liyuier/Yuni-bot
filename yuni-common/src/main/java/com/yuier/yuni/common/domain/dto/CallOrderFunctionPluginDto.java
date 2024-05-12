package com.yuier.yuni.common.domain.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: CallOrderFunctionPluginDto
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.dto
 * @Date 2024/5/13 0:43
 * @description: 命中指令探测器后，调用 function 模块使用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallOrderFunctionPluginDto {
    private String pluginId;
    JsonNode messageEventNode;
    OrderMatchedOut orderMatchedOut;
}
