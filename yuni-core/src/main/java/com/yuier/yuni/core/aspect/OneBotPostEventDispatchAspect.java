package com.yuier.yuni.core.aspect;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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

    @Autowired
    AsyncService asyncService;

    @Autowired
    CoreGlobalData coreGlobalData;

    // 切入点为打了 @OneBotPostEntrance 注解的方法
    @Around("@annotation(com.yuier.yuni.common.annotation.OneBotPostEntrance)")
    public Object dispatchBasedOnPostType(ProceedingJoinPoint joinPoint) throws Throwable {
        // 解析入参
        JsonNode postEventDto = (JsonNode) joinPoint.getArgs()[SystemConstants.FIRST_INDEX];
        coreGlobalData.setPostEventNode(postEventDto);
        String postType = postEventDto.get(SystemConstants.POST_KEY_FIELD.POST_TYPE).asText();


        // 遍历打了 @OneBotEventHandler 注解的类
        Map<String, Object> handlerBeans = applicationContext.getBeansWithAnnotation(OneBotEventHandler.class);
        for (Object bean : handlerBeans.values()) {
            OneBotEventHandler annotation = bean.getClass().getAnnotation(OneBotEventHandler.class);
            if (annotation.eventType().toString().equals(postType)) {
                return asyncService.asyncReflective(bean, SystemConstants.PLUGIN_CRITICAL_NAME.HANDLER_ENTRY, postEventDto).get();
            }
        }

        // 如果没有找到匹配的方法，可以继续执行原始请求或者返回错误
        return joinPoint.proceed();
    }

}
