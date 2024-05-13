package com.yuier.yuni.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.domain.dto.CallBaseFunctionPluginDto;
import com.yuier.yuni.common.domain.dto.CallOrderFunctionPluginDto;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: CallFunction
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/4/16 20:58
 * @description: 调用 function 服务工具类
 */
@Component
public class CallFunction {
    @Autowired
    YuniHttpService yuniHttpService;

    @Value("${base-urls.function}")
    private String baseUrl;

    private String getBaseUrl() {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl;
    }

    public ResponseResult callBaseFunctionPlugin(CallBaseFunctionPluginDto callBaseFunctionPluginDto) {
        String url = getBaseUrl() + "function/plugin/base";
        return yuniHttpService.postRequestForObject(url, callBaseFunctionPluginDto, ResponseResult.class);
    }

    public ResponseResult callOrderFunctionPlugin(CallOrderFunctionPluginDto callOrderFunctionPluginDto) {
        String url = getBaseUrl() + "function/plugin/order";
        return yuniHttpService.postRequestForObject(url, callOrderFunctionPluginDto, ResponseResult.class);
    }
}
