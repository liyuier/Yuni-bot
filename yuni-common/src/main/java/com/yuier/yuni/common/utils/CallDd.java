package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${base-urls.dd}")
    private String baseUrl;

    private String getBaseUrl() {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl;
    }
}
