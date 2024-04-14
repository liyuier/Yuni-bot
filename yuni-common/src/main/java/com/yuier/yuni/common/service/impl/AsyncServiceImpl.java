package com.yuier.yuni.common.service.impl;

import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
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
    public CompletableFuture<Object> asyncReflectiveMethodCall(Object bean, Map<String, Object> postEventDto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        OneBotEventHandler annotation = bean.getClass().getAnnotation(OneBotEventHandler.class);
        Method handle = bean.getClass().getDeclaredMethod(SystemConstants.HANDLE_METHODS, Map.class);
        // 使用反射调用方法
        handle.setAccessible(true);
//        return (CompletableFuture<Object>) handle.invoke(bean, postEventDto);
        return CompletableFuture.completedFuture(handle.invoke(bean, postEventDto));
    }
}
