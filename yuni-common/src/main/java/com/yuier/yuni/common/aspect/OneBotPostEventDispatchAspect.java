package com.yuier.yuni.common.aspect;

import com.yuier.yuni.common.annotation.OneBotEventHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class OneBotPostEventDispatchAspect {

    // 切入点为打了 @OneBotEventUnifiedEntrance 注解的方法
    @Around("@annotation(com.yuier.yuni.common.annotation.OneBotEventEntrance)")
    public Object dispatchBasedOnPostType(ProceedingJoinPoint joinPoint) throws Throwable {
        // 解析入参
        Object oneBotPostEventDto = joinPoint.getArgs()[0];
        Field field = oneBotPostEventDto.getClass().getDeclaredField("post_type");
        field.setAccessible(true);
        String postType = (String) field.get(oneBotPostEventDto);

        // 通过反射，获取入口类的自定义方法
        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();
        // 搜索打上了 @OneBotEventHandler 注解的方法
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(OneBotEventHandler.class)
                    && method.getAnnotation(OneBotEventHandler.class).value().equals(postType)) {
                // 使用反射调用方法
                method.setAccessible(true);
                return method.invoke(joinPoint.getTarget(), oneBotPostEventDto);
            }
        }

        // 如果没有找到匹配的方法，可以继续执行原始请求或者返回错误
        return joinPoint.proceed();
    }

}
