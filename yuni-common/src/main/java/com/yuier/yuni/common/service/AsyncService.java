package com.yuier.yuni.common.service;

import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.InvocationTargetException;
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
    public CompletableFuture<Object> asyncReflectiveMethodCall(Object bean, Map<String, Object> postEventDto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
