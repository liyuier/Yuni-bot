package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Title: CallDd
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/4/16 20:59
 * @description: 调用 dd 服务工具类
 */
@Component
public class CallDd {
    @Autowired
    YuniHttpService yuniHttpService;
    @Autowired
    private Environment environment;

//    @Value("${base-urls.dd}")
//    private String baseUrl;

    private String getBaseUrl() {
//        String baseUrl = environment.getProperty("base-urls.dd");
        String baseUrl = "http://localhost:11453/";
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl;
    }
}
