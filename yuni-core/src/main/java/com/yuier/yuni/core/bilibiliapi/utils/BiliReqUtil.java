package com.yuier.yuni.core.bilibiliapi.utils;

import com.yuier.yuni.core.bilibiliapi.res.AbstractData;
import com.yuier.yuni.core.bilibiliapi.res.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

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
    private String cookies;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders DEFAULT_HEADERS;

    String APP_KEY = "4409e2ce8ffd12b8";
    String APP_SEC = "59b43e04ad6965f34319062b478f83dd";

    public BiliReqUtil() {
        DEFAULT_HEADERS = new HttpHeaders();
        DEFAULT_HEADERS.add("User-Agent", userAgent);
        DEFAULT_HEADERS.add("Referer", referer);
        DEFAULT_HEADERS.setContentType(MediaType.APPLICATION_JSON);
    }

    private void appSignParams(Map<String, String> params) {

    }

    private void wbiSignParams(Map<String, String> params) {

    }

    public <T extends AbstractData> T getBiliApiForData(String url, Map<String, String> params, Class<ApiData<T>> resData) {
        HttpEntity<?> entity = new HttpEntity<>(DEFAULT_HEADERS);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (String key : params.keySet()) {
            builder.queryParam(key, params.get(key));
        }
        try {
            // 发送GET请求并获取响应体
            ApiData<T> response = restTemplate.exchange(
                    builder.build().encode().toString(),
                    HttpMethod.GET,
                    entity,
                    resData
            ).getBody();
            assert response != null;
            return response.getData();
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }

}
