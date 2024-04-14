package com.yuier.yuni.common.domain.message;

import com.yuier.yuni.common.domain.message.data.MessageData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Title: OneBotMessageSegDto
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.dto
 * @Date 2024/4/10 1:21
 * @description: OneBot 消息段类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageSeg {

    // 消息段类型
    private String type;

    // 消息段数据
    private MessageData data;
}
