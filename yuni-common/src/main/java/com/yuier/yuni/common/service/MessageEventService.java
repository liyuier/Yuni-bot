package com.yuier.yuni.common.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.domain.message.MessageEvent;

/**
 * @Title: MessageEventService
 * @Author yuier
 * @Package com.yuier.yuni.common.service
 * @Date 2024/4/14 23:55
 * @description: 消息事件 service 接口
 */
public interface MessageEventService {

    <T> T postToMessage(JsonNode postDataNode, Class<T> targetClazz);

    MessageEvent buildMessageEvent(JsonNode postDataNode);
}
