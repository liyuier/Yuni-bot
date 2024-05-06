package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.domain.message.dto.function.FunctionPluginsDto;
import com.yuier.yuni.common.domain.message.dto.function.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        String url = getBaseUrl() + "function/init_func";
        return yuniHttpService.postRequestForObject(url, pluginFunctionDto, ResponseResult.class);
    }

    public ResponseResult initialPluginsToCore(FunctionPluginsDto functionPluginsDto) {
        String url = getBaseUrl() + "function/init_func_plugins";
        return yuniHttpService.postRequestForObject(url, functionPluginsDto, ResponseResult.class);
    }

    public ResponseResult initialBaseDetectorPluginToCore(BaseDetectorPluginsDto baseDetectorPluginsDto) {
        String url = getBaseUrl() + "function/init_func_base_plugins";
        return yuniHttpService.postRequestForObject(url, baseDetectorPluginsDto, ResponseResult.class);
    }
}
