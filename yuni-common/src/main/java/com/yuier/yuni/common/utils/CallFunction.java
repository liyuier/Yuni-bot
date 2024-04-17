package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
    @Autowired
    private Environment environment;

//    @Value("${base-urls.function}")
//    private String baseUrl;

    private String getBaseUrl() {
//        String baseUrl = environment.getProperty("base-urls.function");
        String baseUrl = "http://localhost:11452/";
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl;
    }

    public ResponseResult orderCallFunction(MessageEvent messageEvent) {
        String url = getBaseUrl() + "function/order";
        return yuniHttpService.postRequestForObject(url, messageEvent, ResponseResult.class);
    }
}
