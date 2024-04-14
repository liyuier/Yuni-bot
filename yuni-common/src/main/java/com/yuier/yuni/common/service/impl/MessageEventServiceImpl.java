package com.yuier.yuni.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Title: MessageEventServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.common.service.impl
 * @Date 2024/4/14 23:55
 * @description: 消息事件接口实现类
 */
@Service
public class MessageEventServiceImpl implements MessageEventService {

    @Autowired
    MessageChainService messageChainService;

    @Override
    public <T> T postToMessage(Map<String, Object> postDataMap, Class<T> targetClazz) {
        T targetObj = null;
        try {
            targetObj = targetClazz.getDeclaredConstructor().newInstance();
            Field[] fields = targetClazz.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isPrivate(mod)) {
                    field.setAccessible(true);
                }
                String fieldName = field.getName();
                Object value = postDataMap.get(StrUtil.toUnderlineCase(fieldName));
                if (value != null) {
                    if (fieldName.equals(SystemConstants.ONE_BOT_POST_TYPE.MESSAGE)) {
                        field.set(targetObj, messageChainService.buildChain((ArrayList<Map<String, Object>>) value));
                    } else if (value instanceof Map) {
                        Class<?> targetType = field.getType();
                        field.set(targetObj, postToMessage((Map<String, Object>) value, targetType));
                    } else {
                        field.set(targetObj, value);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetObj;
    }
}
