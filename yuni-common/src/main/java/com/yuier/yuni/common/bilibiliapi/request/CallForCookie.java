package com.yuier.yuni.common.bilibiliapi.request;

import com.yuier.yuni.common.bilibiliapi.dto.ApiData;
import com.yuier.yuni.common.bilibiliapi.dto.login.CookieInfo;
import com.yuier.yuni.common.bilibiliapi.dto.login.RefreshCookieRes;
import com.yuier.yuni.common.bilibiliapi.utils.BiliReqUtil;
import com.yuier.yuni.common.enums.BiliAuthenticateMethodEnum;
import com.yuier.yuni.common.service.YuniHttpService;
import com.yuier.yuni.common.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: CallForCookie
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.request
 * @Date 2024/6/2 3:20
 * @description: 调用 cookie 相关接口
 */
@Slf4j
@Component
public class CallForCookie {
    @Autowired
    BiliReqUtil biliReqUtil;
    @Autowired
    YuniHttpService yuniHttpService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisCache redisCache;

    private String basePassportUrl = "https://passport.bilibili.com/";
    private String baseUrl = "https://www.bilibili.com/";

    @Value("${headers.cookies.bilibili}")
    private String cookie;

    public CookieInfo checkCookieInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("csrf", getValueFromCookie(cookie, "bili_jct"));
        ParameterizedTypeReference<ApiData<CookieInfo>> typeReference = new ParameterizedTypeReference<>() {};
        ApiData<CookieInfo> apiData = biliReqUtil.getBiliApiForData(
                basePassportUrl + "x/passport-login/web/cookie/info",
                params,
                BiliAuthenticateMethodEnum.COOKIE,
                typeReference
        );
        if (apiData.getCode() != 0) {
            return null;
        }
        return apiData.getData();
    }

    public String getRefreshCsrf(String correspondPath) {
        String url = baseUrl + "correspond/1/" + correspondPath;
        String htmlStr = biliReqUtil.getBiliApiForString(url, new HashMap<>(), BiliAuthenticateMethodEnum.COOKIE);
        Document doc = Jsoup.parse(htmlStr); // 解析 HTML 字符串为 Jsoup 文档对象
        Element div = doc.getElementById("1-name"); // 通过 ID 获取 div 元素
        if (div != null) {
            return div.text();
        } else {
            return null;
        }
    }

    public RefreshCookieRes refreshCookie(String refreshCsrf, String newCookieStr, List<String> setCookieList) {
        String url = basePassportUrl + "x/passport-login/web/cookie/refresh";
        HashMap<String, String> params = new HashMap<>();
        params.put("csrf", getValueFromCookie(cookie, "bili_jct"));
        params.put("refresh_csrf", refreshCsrf);
        params.put("source", "main_web");
        params.put("refresh_token", getValueFromCookie(cookie, "ac_time_value"));
        ParameterizedTypeReference<ApiData<RefreshCookieRes>> typeReference = new ParameterizedTypeReference<>() {};
        ResponseEntity<ApiData<RefreshCookieRes>> apiDataResponseEntity = biliReqUtil.postBiliApiForResponse(url, params, BiliAuthenticateMethodEnum.COOKIE, typeReference);
        ApiData<RefreshCookieRes> apiData = apiDataResponseEntity.getBody();
        HttpHeaders headers = apiDataResponseEntity.getHeaders();

        /**
         * 明天过后只有 chat-GPT 能看懂下面这坨东西是什么
         */
        setCookieList.addAll(Objects.requireNonNull(headers.get("Set-Cookie")));
        String oldCookie = cookie;
        String[] oldCookieEleArr = oldCookie.split("; ");
        for (String setCookieStr : setCookieList) {
            String setCookiePairStr = setCookieStr.split(";")[0];
            for (String oldCookieEle : oldCookieEleArr) {
                if (oldCookieEle.startsWith(setCookiePairStr.split("=")[0] + "=")) {
                    oldCookieEle = setCookiePairStr;
                }
            }
        }
        StringBuilder newCookie = new StringBuilder();
        for (String newCookieEle : oldCookieEleArr) {
            newCookie.append(newCookieEle).append("; ");
        }
        newCookieStr = newCookie.toString();

        if (apiData.getCode() != 0) {
            return null;
        }
        return apiData.getData();
    }

    private String getValueFromCookie(String cookie, String key) {
        String value = "";
        Matcher matcher = Pattern.compile(key + "=(\\w+)").matcher(cookie);
        if (matcher.find()) {
            value = matcher.group(1);
        } else {
            log.info("Cookie 中没有 " + key + " 字段，请补齐！");
            return null;
        }
        return value;
    }
}
