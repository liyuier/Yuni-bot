package com.yuier.yuni.common.bilibiliapi.utils;

import com.yuier.yuni.common.bilibiliapi.dto.AbstractData;
import com.yuier.yuni.common.bilibiliapi.dto.ApiData;
import com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo.UserCardInfo;
import com.yuier.yuni.common.enums.BiliAuthenticateMethodEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Title: BiliReqUtil
 * @Author yuier
 * @Package com.yuier.yuni.core.bilibiliapi.utils
 * @Date 2024/5/19 19:00
 * @description: 向 bilibili api 发送 http 请求的工具
 */
@Component
public class BiliReqUtil {

    @Value("${headers.user-agent}")
    private String userAgent;

    @Value("${headers.referer.bilibili}")
    private String referer;

    @Value("${headers.cookies.bilibili}")
    private String cookie;

    private String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7";

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders DEFAULT_HEADERS;

    String APP_KEY = "1d8b6e7d45233436";
    String APP_SEC = "560c52ccd288fed045859ed18bffd973";


    private HttpEntity getBaseHeader() {
        DEFAULT_HEADERS = new HttpHeaders();
        DEFAULT_HEADERS.add("User-Agent", userAgent);
        DEFAULT_HEADERS.add("Referer", referer);
        DEFAULT_HEADERS.add("Cookie", cookie);
        DEFAULT_HEADERS.add("Accept", accept);
        HttpEntity<?> entity = new HttpEntity<>(DEFAULT_HEADERS);
        return entity;
    }

    private static String generateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void appSignParams(Map<String, String> params) {
        // 为请求参数进行 APP 签名
        params.put("appkey", APP_KEY);
        // 按照 key 重排参数
        Map<String, String> sortedParams = new TreeMap<>(params);
        // 序列化参数
        StringBuilder queryBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (!queryBuilder.isEmpty()) {
                queryBuilder.append('&');
            }
            queryBuilder
                    .append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append('=')
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        params.put("sign", generateMD5(queryBuilder .append(APP_SEC).toString()));
    }

    private void wbiSignParams(Map<String, String> params) {

    }

    public <T extends AbstractData> ApiData<T> getBiliApiForData(String url, HashMap<String, String> params, BiliAuthenticateMethodEnum authenticateMethod, ParameterizedTypeReference<ApiData<T>> typeReference) {
        // 对参数签名
        if (authenticateMethod.equals(BiliAuthenticateMethodEnum.APP_SIGN)) {
            appSignParams(params);
        }
        if (authenticateMethod.equals(BiliAuthenticateMethodEnum.WBI_SIGN)){
            wbiSignParams(params);
        }
        HttpEntity<?> entity = getBaseHeader();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (String key : params.keySet()) {
            builder.queryParam(key, params.get(key));
        }
        try {
            // 发送GET请求并获取响应体
            ResponseEntity<ApiData<T>> response = restTemplate.exchange(
                    builder.build().encode().toString(),
                    HttpMethod.GET,
                    entity,
                    typeReference
            );
            return response.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }

}
