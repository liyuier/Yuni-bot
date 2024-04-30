package com.yuier.yuni.common.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: HttpServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.common.service.impl
 * @Date 2024/4/15 22:25
 * @description: HttpService 实现类
 */
@Service
public class YuniHttpServiceImpl implements YuniHttpService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T getRequestForObject(String url, Class<T> responseType) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, responseType);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data from " + url + ". Status code: " + responseEntity.getStatusCode());
        }
    }

    @Override
    public<S, T> T postRequestForObject(String url, S requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<S> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<T> responseEntity  = restTemplate.postForEntity(url, requestEntity, responseType);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to post data to " + url + ". Status code: " + responseEntity.getStatusCode());
        }
    }

    @Override
    public<T> T postRequestForObject(String url, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> responseEntity  = restTemplate.postForEntity(url, requestEntity, responseType);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to post data to " + url + ". Status code: " + responseEntity.getStatusCode());
        }
    }

    @Override
    public <S> JsonNode postRequestForJsonNode(String url, S requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<S> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<JsonNode> responseEntity  = restTemplate.postForEntity(url, requestEntity, JsonNode.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to post data to " + url + ". Status code: " + responseEntity.getStatusCode());
        }
    }

    @Override
    public JsonNode postRequestForJsonNode(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<JsonNode> responseEntity  = restTemplate.postForEntity(url, requestEntity, JsonNode.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to post data to " + url + ". Status code: " + responseEntity.getStatusCode());
        }
    }
}
