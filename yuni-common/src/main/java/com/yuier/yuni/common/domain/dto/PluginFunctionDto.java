package com.yuier.yuni.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Title: PluginFunctionDto
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.dto
 * @Date 2024/4/16 22:04
 * @description: 在 core 和 function 之间传递插件功能数据的类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PluginFunctionDto {
    private HashMap<String, ArrayList<String>> functionMap;
}
