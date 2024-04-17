package com.yuier.yuni.common.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.service.AsyncService;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Title: AsyncServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.common.service.impl
 * @Date 2024/4/15 2:07
 * @description: 异步调用代理接口实现类
 */
@Service
public class AsyncServiceImpl implements AsyncService{
    @Override
    public CompletableFuture<Object> asyncReflectiveHandler(Object bean, Map<String, Object> postEventDto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method handle = bean.getClass().getDeclaredMethod(SystemConstants.HANDLE_METHODS, Map.class);
        // 使用反射调用方法
        handle.setAccessible(true);
        return CompletableFuture.completedFuture(handle.invoke(bean, postEventDto));
    }

    @Override
    public CompletableFuture<Object> asyncReflectiveDetector(Object bean, MessageEvent messageEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method detect = bean.getClass().getDeclaredMethod("detect", MessageEvent.class);
        detect.setAccessible(true);
        return CompletableFuture.completedFuture(detect.invoke(bean, messageEvent));
    }

    @Override
    public CompletableFuture<Object> asyncReflectivePlugin(Object bean, MessageEvent messageEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method run = bean.getClass().getDeclaredMethod("run", MessageEvent.class);
        run.setAccessible(true);
        return CompletableFuture.completedFuture(run.invoke(bean, messageEvent));
    }

    @Override
    public CompletableFuture<Object> asyncReflectiveHandler(Object bean, JsonNode postEventDto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method run = bean.getClass().getDeclaredMethod(SystemConstants.HANDLE_METHODS, JsonNode.class);
        run.setAccessible(true);
        return CompletableFuture.completedFuture(run.invoke(bean, postEventDto));
    }

    @Override
    public CompletableFuture<Object> asyncReflectiveDetector(Object bean, MessageChain chain) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method detect = bean.getClass().getDeclaredMethod("detect", MessageChain.class);
        detect.setAccessible(true);
        return CompletableFuture.completedFuture(detect.invoke(bean, chain));
    }
}
