package com.yuier.yuni.core.domain.event.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yuier.yuni.core.domain.message.AnonymousMessage;
import com.yuier.yuni.core.domain.message.MessageSeg;
import com.yuier.yuni.core.domain.message.MessageSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Title: OneBotMessageEventDto
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.dto
 * @Date 2024/4/10 1:06
 * @description: OneBot 上报消息事件 DTO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageEvent {

    // 收到消息的时间戳
    private Date time;

    // 收到消息的机器人QQ号
    private Long selfId;

    // 事件类型
    private String postType;

    /**
     * 消息类型。如果是私聊消息，为 private；如果是群聊消息，为group
     */
    private String messageType;

    /**
     * 消息子类型
     如果是私聊消息：如果是好友则是 friend，如果是群临时会话则是 group。
     如果是 群聊消息：正常消息是 normal，匿名消息是 anonymous，系统提示（如【管理员已禁止群内匿名聊天】）是 notice。
     */
    private String subType;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 发送者 QQ 号
     */
    private Long userId;

    /**
     * 消息内容
     */
    private List<MessageSeg> message;

    /**
     * 原始消息内容
     */
    private String rawMessage;

    /**
     * 字体
     */
    private int font;

    /**
     * 发送人信息
     */
    private MessageSender sender;

    /**
     * 群号。只有为群聊消息时有效。
     */
    private Long groupId;

    /**
     * 匿名信息，如果不是匿名消息则为 null
     */
    private AnonymousMessage anonymous;
}