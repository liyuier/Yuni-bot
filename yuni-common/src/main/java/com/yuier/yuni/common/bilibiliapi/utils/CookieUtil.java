package com.yuier.yuni.common.bilibiliapi.utils;

import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Title: CookieUtil
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.utils
 * @Date 2024/6/14 0:18
 * @description: B 站 cookie 相关的工具
 */
@Component
public class CookieUtil {
    @Autowired
    RedisCache redisCache;

    public String getRedisBiliCookie() {
        return redisCache.getCacheObject(SystemConstants.REDIS_KEY.BILI_COOKIE);
    }

    public String getRedisBiliRefreshToken() {
        return redisCache.getCacheObject(SystemConstants.REDIS_KEY.REFRESH_TOKEN);
    }

    public void setRedisBiliCookie(String cookie) {
        redisCache.setCacheObject(SystemConstants.REDIS_KEY.BILI_COOKIE, cookie);
    }

    public void setRedisBiliRefreshToken(String refreshToken) {
        redisCache.setCacheObject(SystemConstants.REDIS_KEY.REFRESH_TOKEN, refreshToken);
    }

    public HashMap<String, String> cookieToMap(String cookie) {
        HashMap<String, String> cookieMap = new HashMap<>();
        String[] cookieEleArr = cookie.split("; ");
        for (String cookieEle : cookieEleArr) {
            String[] cookieElePair = cookieEle.split("=");
            cookieMap.put(cookieElePair[0], cookieElePair[1]);
        }
        return cookieMap;
    }

    public String mapToCookie(HashMap<String, String> cookieMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : cookieMap.keySet()) {
            stringBuilder.append(key).append("=").append(cookieMap.get(key)).append("; ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}
