package com.yuier.yuni.common.listener.dto;

import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.listener.MessageTypeListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: MessageTypeListenerDto
 * @Author yuier
 * @Package com.yuier.yuni.common.listener.dto
 * @Date 2024/5/7 22:02
 * @description: 消息类型探测器 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageTypeListenerDto {
    private MessageTypeEnum listener;

    public MessageTypeListenerDto(MessageTypeListener listener) {
        this.listener = listener.getListener();
    }
}
