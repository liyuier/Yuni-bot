package com.yuier.yuni.common.service;


import com.fasterxml.jackson.databind.JsonNode;

/**
 * @Title: HttpService
 * @Author yuier
 * @Package com.yuier.yuni.common.service
 * @Date 2024/4/15 22:24
 * @description: 模块之间使用 HTTP 互相通信的底层服务
 */
public interface YuniHttpService {

    <S, T> T postRequestForObject(String url, S requestBody, Class<T> responseType);
    <T> T postRequestForObject(String url, Class<T> responseType);

    <S> JsonNode postRequestForJsonNode(String url, S requestBody);
    JsonNode postRequestForJsonNode(String url);

    <T> T getRequestForObject(String url, Class<T> responseType);
}
