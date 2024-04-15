package com.yuier.yuni.common.service.impl;

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

    public <T> T getRequestForObject(String url, Class<T> responseType) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, responseType);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data from " + url + ". Status code: " + responseEntity.getStatusCode());
        }
    }

    public<S, T> T postRequestForObject(String url, S requestBody, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<S> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<T> responseEntity  = restTemplate.postForEntity(url, requestEntity, clazz);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Failed to post data to " + url + ". Status code: " + responseEntity.getStatusCode());
        }
    }
}
