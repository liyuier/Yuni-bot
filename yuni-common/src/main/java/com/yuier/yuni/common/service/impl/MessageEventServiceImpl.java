package com.yuier.yuni.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.MessageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
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
                JsonNode value = postDataNode.get(StrUtil.toUnderlineCase(fieldName));
                if (value != null) {
                    if (fieldName.equals("message")) {
                        field.set(targetObj, messageChainService.buildChain((ArrayNode) value));
                    } else if (value instanceof ObjectNode) {
                        Class<?> targetType = field.getType();
                        field.set(targetObj, postToMessage(value, targetType));
                    } else { // TODO 重写
                        if (value.isTextual()) {
                            // 如果字段是文本，则直接设置值  
                            field.set(targetObj, value.asText());
                        } else if (value.isBoolean()) {
                            field.set(targetObj, value.asBoolean());
                        } else if (value.isInt()) {
                            field.set(targetObj, (long)value.asInt());
                        } else if (value.isLong()) {
                            field.set(targetObj, value.asLong());
                        } else if (value.isFloat() || value.isDouble()) {
                            field.set(targetObj, value.asDouble());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetObj;
    }
}
