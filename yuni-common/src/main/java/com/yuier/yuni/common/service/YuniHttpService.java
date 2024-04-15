package com.yuier.yuni.common.service;


/**
 * @Title: HttpService
 * @Author yuier
 * @Package com.yuier.yuni.common.service
 * @Date 2024/4/15 22:24
 * @description: 模块之间使用 HTTP 互相通信的底层服务
 */
public interface YuniHttpService {

    <T> T sendPostRequest(String url, Object requestBody, Class<T> clazz);

    <T> T getRequestForObject(String url, Class<T> responseType);
}
