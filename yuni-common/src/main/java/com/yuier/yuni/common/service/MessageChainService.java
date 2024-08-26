package com.yuier.yuni.common.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.data.MessageData;

/**
 * @Title: MessageChainService
 * @Author yuier
 * @Package com.yuier.yuni.core.service
 * @Date 2024/4/14 3:15
 * @description: 消息链 service 接口
 */
public interface MessageChainService {

    MessageChain buildChain(ArrayNode msgMapList);

    MessageData buildMessageData(JsonNode node);

    MessageChain buildChain(String msgStr);

    MessageChain buildFileChain(String file);

    MessageChain buildImageChainByLocalFile(String image);

    MessageChain buildImageChainByUrl(String url);

}
