package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.domain.message.dto.SendMessageDto;
import com.yuier.yuni.common.domain.message.dto.SendPrivateMessageDto;
import com.yuier.yuni.common.domain.message.res.SendMessageRes;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Title: CallOneBotUtils
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/4/15 22:14
 * @description: 调用 OneBot 实现 API 的工具类
 */
@Component
public class CallOneBot {
    @Autowired
    YuniHttpService yuniHttpService;

    @Value("${base-urls.one-bot}")
    private String baseUrl;

    private String getBaseUrl() {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl;
    }

    public SendMessageRes sendMessage(SendMessageDto dto) {
        String url = getBaseUrl() + "send_msg";
        return yuniHttpService.postRequestForObject(url, dto, SendMessageRes.class);
    }

    public SendMessageRes sendPrivateMessage(SendPrivateMessageDto dto) {
        String url = getBaseUrl() + "send_msg";
        return yuniHttpService.postRequestForObject(url, dto, SendMessageRes.class);
    }

    public SendMessageRes sendGroupMessage(SendGroupMessageDto dto) {
        String url = getBaseUrl() + "send_msg";
        return yuniHttpService.postRequestForObject(url, dto, SendMessageRes.class);
    }
}
