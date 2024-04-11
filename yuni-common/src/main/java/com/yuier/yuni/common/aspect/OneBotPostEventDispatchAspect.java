package com.yuier.yuni.common.aspect;

import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Title: OneBotPostEventDispatchAspect
 * @Author yuier
 * @Package com.yuier.yuni.common.aspect
 * @Date 2024/4/10 0:41
 * @description: OneBot上报事件分派切面，根据 post_type 的值分派不同的处理方法
 */

@Aspect
@Component
public class OneBotPostEventDispatchAspect {

    @Autowired
    ApplicationContext applicationContext;

    // 切入点为打了 @OneBotEventUnifiedEntrance 注解的方法
    @Around("@annotation(com.yuier.yuni.common.annotation.OneBotPostEntrance)")
    public Object dispatchBasedOnPostType(ProceedingJoinPoint joinPoint) throws Throwable {
        // 解析入参
        Object oneBotPostEventDto = joinPoint.getArgs()[0];
        Field field = oneBotPostEventDto.getClass().getDeclaredField(SystemConstants.FIELD_NAMES.POST_TYPE);
        field.setAccessible(true);
        String postType = (String) field.get(oneBotPostEventDto);

        Map<String, Object> handlerBeans = applicationContext.getBeansWithAnnotation(OneBotEventHandler.class);
        for (Object bean : handlerBeans.values()) {
            OneBotEventHandler annotation = bean.getClass().getAnnotation(OneBotEventHandler.class);
            if (annotation.eventType().equals(postType)) {
                Method handle = bean.getClass().getDeclaredMethod(SystemConstants.HANDLE_METHODS, oneBotPostEventDto.getClass());
                // 使用反射调用方法
                handle.setAccessible(true);
                return handle.invoke(bean, oneBotPostEventDto);
            }
        }
//        // 通过反射，获取入口类的自定义方法
//        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();
//        // 搜索打上了 @OneBotEventHandler 注解的方法
//        for (Method method : declaredMethods) {
//            if (method.isAnnotationPresent(OneBotEventEntrance.class)
//                    && method.getAnnotation(OneBotEventEntrance.class).eventType().equals(postType)) {
//                // 使用反射调用方法
//                method.setAccessible(true);
//                return method.invoke(joinPoint.getTarget(), oneBotPostEventDto);
//            }
//        }

        // 如果没有找到匹配的方法，可以继续执行原始请求或者返回错误
        return joinPoint.proceed();
    }

}
