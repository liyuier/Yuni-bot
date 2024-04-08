package com.yuier.yuni.core.aspect;

import com.yuier.yuni.core.annotation.OneBotEventController;
import com.yuier.yuni.core.annotation.OneBotEventHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class EventDispatchAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Around("@annotation(com.yuier.yuni.core.annotation.OneBotEventUnifiedEntrance)")
    public Object dispatchBasedOnPostType(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String postType = request.getParameter("post_type");
        String str = request.getQueryString();
        Map<String, String[]> map = request.getParameterMap();
        for (String k : map.keySet()) {
            System.out.println(k);
            System.out.println(Arrays.toString(map.get(k)));
        }

        // 查找带有@EventHandler注解的方法
        Map<String, Object> beansWithMethods = applicationContext.getBeansWithAnnotation(OneBotEventController.class);
        for (Object bean : beansWithMethods.values()) {
            Method[] declaredMethods = bean.getClass().getSuperclass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(OneBotEventHandler.class)
                        && method.getAnnotation(OneBotEventHandler.class).value().equals(postType)
                    ) {
                    OneBotEventHandler annotation = method.getDeclaredAnnotation(OneBotEventHandler.class);
                    System.out.println(annotation.value());
                    // 使用反射调用方法
                    method.setAccessible(true);
                    return method.invoke(bean, request);
                }
            }
        }

        // 如果没有找到匹配的方法，可以继续执行原始请求或者返回错误
        return joinPoint.proceed();
    }

}
