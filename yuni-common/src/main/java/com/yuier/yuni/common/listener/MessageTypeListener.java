package com.yuier.yuni.common.listener;

import com.yuier.yuni.common.enums.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: MessageTypeListener
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.listener
 * @Date 2024/5/7 22:01
 * @description: 消息类型（私聊/群聊）探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageTypeListener {
    private MessageTypeEnum listenMessageType;
}
