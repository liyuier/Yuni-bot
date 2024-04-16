package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Title: CallCore
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/4/16 20:57
 * @description: 调用 core 服务工具类
 */
@Component
public class CallCore {
    @Autowired
    YuniHttpService yuniHttpService;

    @Value("${base-urls.core}")
    private String baseUrl;

    private String getBaseUrl() {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl;
    }

    public ResponseResult initializeFunction(PluginFunctionDto pluginFunctionDto) {
        String url = getBaseUrl() + "init_func";
        return yuniHttpService.postRequestForObject(url, pluginFunctionDto, ResponseResult.class);
    }
}
