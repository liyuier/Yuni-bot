package com.yuier.yuni.common.service;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @Title: MessageEventService
 * @Author yuier
 * @Package com.yuier.yuni.common.service
 * @Date 2024/4/14 23:55
 * @description: 消息事件 service 接口
 */
public interface MessageEventService {

    <T> T postToMessage(JsonNode postDataNode, Class<T> targetClazz);
}
