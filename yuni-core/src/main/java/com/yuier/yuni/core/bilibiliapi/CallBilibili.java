package com.yuier.yuni.core.bilibiliapi;

import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: CallBilibili
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/5/16 0:09
 * @description: 调用B站接口的工具
 */
@Component
public class CallBilibili {
    @Autowired
    YuniHttpService yuniHttpService;

    @Value("${api.bilibili.base}")
    private String baseUrl;
}
