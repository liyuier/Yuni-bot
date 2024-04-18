package com.yuier.yuni.common.service.impl;

import com.yuier.yuni.common.service.AsyncService;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public CompletableFuture<Object> asyncReflective(Object targetBean, Object argsObject, String targetMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method targetMethod = targetBean.getClass().getDeclaredMethod(targetMethodName, argsObject.getClass());
        targetMethod.setAccessible(true);
        return CompletableFuture.completedFuture(targetMethod.invoke(targetBean, argsObject));
    }
}
