package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.plugin.dto.function.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.plugin.dto.function.positive.PositivePluginsDto;
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


    public ResponseResult initialBaseDetectorPluginToCore(BaseDetectorPluginsDto baseDetectorPluginsDto) {
        String url = getBaseUrl() + "function/init_func/base";
        return yuniHttpService.postRequestForObject(url, baseDetectorPluginsDto, ResponseResult.class);
    }

    public ResponseResult initialPositivePluginToCore(PositivePluginsDto positivePluginDto) {
        String url = getBaseUrl() + "function/init_func/positive";
        return yuniHttpService.postRequestForObject(url, positivePluginDto, ResponseResult.class);
    }
}
