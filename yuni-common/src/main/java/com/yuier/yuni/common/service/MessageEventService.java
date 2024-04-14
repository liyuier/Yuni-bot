package com.yuier.yuni.common.service;

import java.util.Map;

/**
 * @Title: MessageEventService
 * @Author yuier
 * @Package com.yuier.yuni.common.service
 * @Date 2024/4/14 23:55
 * @description: 消息事件 service 接口
 */
public interface MessageEventService {
    <T> T postToMessage(Map<String, Object> postDataMap, Class<T> targetClazz);
}
