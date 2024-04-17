package com.yuier.yuni.common.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuier.yuni.common.annotation.MessageDataEntity;
import com.yuier.yuni.common.annotation.OneBotEventHandler;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.MessageData;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.domain.message.data.UnknownData;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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

    @Override
    public MessageChain buildChain(ArrayList<Map<String, Object>> msgMapList) {
        MessageChain chain = new MessageChain();
        chain.setContent(new ArrayList<>());
        for (Map<String, Object> msgMap : msgMapList) {
            String type = (String) msgMap.get("type");
            MessageData data = buildMessageData(msgMap);
            MessageSeg messageSeg = new MessageSeg(type, data);
            chain.getContent().add(messageSeg);
        }
        return chain;
    }

    /**
     * 传入 Map，构造出 MessageData 对象
     * @param msgMap 原始的 MessageData 数据
     * @return 构造出的 MessageData 对象
     */
    @Override
    public MessageData buildMessageData(Map<String, Object> msgMap) {
        String type = (String) msgMap.get("type");
        Map<String, Object> mapData = (Map<String, Object>) msgMap.get("data");
        if (null == type || null == mapData) {
            try {
                log.error("消息段结构不全！" + new ObjectMapper().writeValueAsString(msgMap));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        Map<String, Object> msgDataBeans = applicationContext.getBeansWithAnnotation(MessageDataEntity.class);
        for (Object bean : msgDataBeans.values()) {
            if (!(bean instanceof MessageData)) {
                continue;
            }
            MessageDataEntity annotation = bean.getClass().getAnnotation(MessageDataEntity.class);
            if (annotation.messageType().equals(type)) {
                return (MessageData) BeanCopyUtils.mapToMapObject(mapData, bean.getClass());
            }
        }
        throw new RuntimeException("Unknown type: " + type);
    }

    // 传入字符串，创建一个消息链
    @Override
    public MessageChain buildChain(String msgStr) {
        MessageChain chain = new MessageChain();
        chain.setContent(new ArrayList<>());
        chain.getContent().add(new MessageSeg("text", new TextData(msgStr)));
        return chain;
    }
}
