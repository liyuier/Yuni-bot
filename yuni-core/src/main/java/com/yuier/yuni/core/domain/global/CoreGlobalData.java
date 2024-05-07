package com.yuier.yuni.core.domain.global;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.core.detector.base.BasePluginsDetector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: GlobalData
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global
 * @Date 2024/4/16 19:28
 * @description: Core 全局数据类
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CoreGlobalData {

    private JsonNode postEventNode;

    private BasePluginsDetector basePluginsDetector;
}
