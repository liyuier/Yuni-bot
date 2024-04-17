package com.yuier.yuni.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    private static final Map<Class<?>, JsonNodeToFieldValueConverter> converters = new HashMap<>();

    static {
        converters.put(String.class, JsonNode::asText);
        converters.put(Boolean.class, JsonNode::asBoolean);
        converters.put(Integer.class, JsonNode::asInt);
        converters.put(Long.class, JsonNode::asLong);
        converters.put(Double.class, JsonNode::asDouble);
        // 可以根据需要添加更多类型及其转换器
    }

    @FunctionalInterface
    private interface JsonNodeToFieldValueConverter {
        Object convert(JsonNode jsonNode);
    }

    /**
     * 将 map 类型映射为 MessageEvent 类型
     * 由于涉及递归调用，所以入参使用泛型
     * @param postDataMap 要进行映射的 Map
     * @param targetClazz 要映射到的对象的类对象
     * @return 返回要映射到的对象
     * @param <T>
     */
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

    /**
     * 将 JsonNode 对象映射为 MessageEvent 对象。由于涉及递归操作，故入参使用泛型
     * @param postDataNode 要进行映射操作的 JsonNode 对象
     * @param targetClazz 要映射到的对象的类对象
     * @return 返回要映射到的对象
     * @param <T>
     */
    @Override
    public <T> T postToMessage(JsonNode postDataNode, Class<T> targetClazz) {
        T targetObj = null;
        try {
            targetObj = targetClazz.getDeclaredConstructor().newInstance();
            Field[] fields = targetClazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetObj;
    }

    public static void mapJsonNodeToEntity(JsonNode jsonNode, Object entity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();

        ObjectMapper mapper = new ObjectMapper();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            JsonNode fieldNode = jsonNode.get(fieldName);
            if (fieldNode != null) {
                if (fieldNode.isObject()) {
                    // 递归处理嵌套对象
                    Object nestedEntity = field.getType().getDeclaredConstructor().newInstance();
                    mapJsonNodeToEntity(fieldNode, nestedEntity);
                    field.set(entity, nestedEntity);
                } else {
                    JsonNodeToFieldValueConverter converter = converters.get(field.getType());
                    if (converter != null) {
                        field.set(entity, converter.convert(fieldNode));
                    } else {
                        // 对于不支持的类型，可以选择抛出异常或进行其他处理
                        throw new IllegalArgumentException("Unsupported field type: " + field.getType());
                    }
                }
            }
        }
    }
}
