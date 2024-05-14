package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.domain.dto.CallBaseFunctionPluginDto;
import com.yuier.yuni.common.domain.dto.CallOrderFunctionPluginDto;
import com.yuier.yuni.common.enums.YuniModuleEnum;
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
    private String functionBaseUrl;

    @Value("${base-urls.dd}")
    private String ddBaseUrl;

    private String getBaseUrl(YuniModuleEnum module) {
        String baseUrl = "";
        if (module.equals(YuniModuleEnum.FUNCTION)) {
            baseUrl = functionBaseUrl;
        } else if (module.equals(YuniModuleEnum.DD)) {
            baseUrl = ddBaseUrl;
        }
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl + module.getName();
    }

    public ResponseResult callBaseFunctionPlugin(CallBaseFunctionPluginDto callBaseFunctionPluginDto, YuniModuleEnum module) {
        String url = getBaseUrl(module) + "/plugin/base";
        return yuniHttpService.postRequestForObject(url, callBaseFunctionPluginDto, ResponseResult.class);
    }

    public ResponseResult callOrderFunctionPlugin(CallOrderFunctionPluginDto callOrderFunctionPluginDto, YuniModuleEnum module) {
        String url = getBaseUrl(module) + "/plugin/order";
        return yuniHttpService.postRequestForObject(url, callOrderFunctionPluginDto, ResponseResult.class);
    }
}
