package com.yuier.yuni.core.domain.global.detector.listener;

import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.listener.dto.MessageTypeListenerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: MessageTypeListenerForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.listener
 * @Date 2024/5/7 22:13
 * @description: 实际使用的消息类型探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageTypeListenerForUse {
    private MessageTypeEnum listener;

    public MessageTypeListenerForUse(MessageTypeListenerDto dto) {
        this.listener = dto.getListener();
    }

    public Boolean hit(MessageEvent messageEvent) {
        if (listener.equals(MessageTypeEnum.ALL)) {
            return true;
        }
        return messageEvent.getMessageType().equals(listener.getMsgType());
    }
}
