package com.yuier.yuni.core.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * OneBot 上报消息事件类
 *
 * @author liyuier
 * @since 2024-04-08 01:38:57
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneBotPostMessageEventDto {

    // 收到消息的时间戳
    private Date time;

    // 收到消息的机器人QQ号
    private Long self_id;

    // 事件类型
    private String post_type = "message";

    // 消息类型
    private String message_type;

    // 消息子类型
    private String sub_type;

    // 消息 ID
    private Long message_id;

    // 发送者 QQ 号
    private Long user_id;

    // 消息内容
    private OneBotPostMessageDto message;

    // 原始消息内容
    private String raw_message;
}
