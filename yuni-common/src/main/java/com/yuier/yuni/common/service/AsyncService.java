package com.yuier.yuni.common.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Title: AsyncService
 * @Author yuier
 * @Package com.yuier.yuni.common.service
 * @Date 2024/4/15 2:02
 * @description: 异步代理 Bean ，可以异步地执行插件入口方法
 */
public interface AsyncService {

    @Async
    CompletableFuture<Object> asyncReflectiveHandler(Object bean, Map<String, Object> postEventDto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Async
    CompletableFuture<Object> asyncReflectiveDetector(Object bean, MessageEvent messageEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Async
    CompletableFuture<Object> asyncReflectivePlugin(Object bean, MessageEvent messageEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Async
    CompletableFuture<Object> asyncReflectiveHandler(Object bean, JsonNode postEventDto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    @Async
    CompletableFuture<Object> asyncReflectiveDetector(Object bean, MessageChain chain) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
