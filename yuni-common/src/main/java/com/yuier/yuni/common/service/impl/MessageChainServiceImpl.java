package com.yuier.yuni.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.annotation.MessageDataEntity;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.MessageData;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.enums.MessageDataEnum;
import com.yuier.yuni.common.service.MessageChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Title: MessageChainServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.core.service.impl
 * @Date 2024/4/14 3:17
 * @description: 消息链 service 实现类
 */
@Service
@Slf4j
public class MessageChainServiceImpl implements MessageChainService {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 传入 ArrayNode ，构造出 MessageData 对象
     * @param arrayNode 原始的 ArrayNode 类型的消息段数据
     * @return 构造出的 MessageData 对象
     */
    @Override
    public MessageChain buildChain(ArrayNode arrayNode) {
        MessageChain chain = new MessageChain();
        chain.setContent(new ArrayList<>());
        for (JsonNode node : arrayNode) {
            String type = node.get(SystemConstants.POST_KEY_FIELD.MESSAGE_TYPE).asText();
            MessageData data = buildMessageData(node);
            MessageSeg messageSeg = new MessageSeg(type, data);
            chain.getContent().add(messageSeg);
        }
        return chain;
    }

    /**
     * 传入一个 JsonNode 消息段，将消息段中的 data 字段映射为 MessageData 对象
     * @param node 传入的消息段
     * @return 构造出的 MessageData
     */
    @Override
    public MessageData buildMessageData(JsonNode node) {
        String messageType = node.get(SystemConstants.POST_KEY_FIELD.MESSAGE_TYPE).asText();
        ObjectNode messageDataNode = (ObjectNode) node.get(SystemConstants.POST_KEY_FIELD.MESSAGE_DATA);
        if (null == messageType || null == messageDataNode) {
            log.error("消息段结构不全！" + node);
        }
        Map<String, Object> msgDataBeans = applicationContext.getBeansWithAnnotation(MessageDataEntity.class);
        for (Object bean : msgDataBeans.values()) {
            // 我也不知道当时为什么要加这么一个判断，可能是防止自己没有继承基础父类吧
            if (!(bean instanceof MessageData)) {
                continue;
            }
            MessageDataEntity annotation = bean.getClass().getAnnotation(MessageDataEntity.class);
            if (annotation.dataType().toString().equals(messageType)) {
                return objectNodeToMessageData(messageDataNode, (Class<MessageData>) bean.getClass());
            }
        }
        throw new RuntimeException("无法识别的消息段类型：" + messageType);
    }

    /**
     * 传入消息段中的 data 字段与要构造的 MessageData 类对象
     * @param objectNode
     * @param targetMessageClazz
     * @return
     */
    private MessageData objectNodeToMessageData(ObjectNode objectNode, Class<MessageData> targetMessageClazz) {
        MessageData messageData = null;
        try {
            messageData = targetMessageClazz.getDeclaredConstructor().newInstance();
            Field[] fields = targetMessageClazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                JsonNode nodeFieldVal = objectNode.get(StrUtil.toUnderlineCase(field.getName()));
                String msgDataFieldVal;
                if (null != nodeFieldVal) {
                    msgDataFieldVal = nodeFieldVal.asText();
                    field.set(messageData, msgDataFieldVal);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageData;
    }

    // 传入字符串，创建一个消息链
    @Override
    public MessageChain buildChain(String msgStr) {
        MessageChain chain = new MessageChain();
        chain.setContent(new ArrayList<>());
        chain.getContent().add(new MessageSeg(MessageDataEnum.TEXT.toString(), new TextData(msgStr)));
        return chain;
    }
}
