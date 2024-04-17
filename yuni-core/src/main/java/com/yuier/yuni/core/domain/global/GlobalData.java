package com.yuier.yuni.core.domain.global;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

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
public class GlobalData {
    private HashMap<String, ArrayList<String>> functions;

    private JsonNode postEventNode;
}
