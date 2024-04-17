package com.yuier.yuni.common.service;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.data.MessageData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: MessageChainService
 * @Author yuier
 * @Package com.yuier.yuni.core.service
 * @Date 2024/4/14 3:15
 * @description: 消息链 service 接口
 */
public interface MessageChainService {
    MessageChain buildChain(ArrayList<Map<String, Object>> msgMapList);

    MessageData buildMessageData(Map<String, Object> msgMap);

    MessageChain buildChain(String msgStr);

}
