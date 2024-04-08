package com.yuier.yuni.core.domain.entity;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * (MessageRecord)表实体类
 *
 * @author liyuier
 * @since 2024-04-08 01:38:57
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRecordEntity {
    
    // id
    private Long id;
    
    // 收到消息的时间戳
    private Date time;
    
    // 收到消息的机器人QQ号
    private Long selfId;
    
    // 消息类型。如果是私聊消息，为 private；如果是群聊消息，为group
    private String messageType;
    
    // 消息子类型
    // 如果是私聊消息：如果是好友则是 friend，如果是群临时会话则是 group。
    // 如果是 群聊消息：正常消息是 normal，匿名消息是 anonymous，系统提示（如【管理员已禁止群内匿名聊天】）是 notice。
    private String subType;
    
    // 消息ID
    private Long messageId;
    
    // 群号。只有为群聊消息时有效。
    private Long groupId;
    
    // 发送者 QQ 号
    private Long userId;
    
    // 默认0，表示不是匿名信息；1表示是匿名信息。只有当群聊消息时有效。
    private Integer anonymous;
    
    // 匿名用户ID
    private Long anonymousId;
    
    // 匿名用户名称
    private String anonymousName;
    
    // 匿名用户 flag，调用禁言API时传入
    private String anonymousFlag;
    
    // 消息内容
    private String message;
    
    // 原始消息内容
    private String rawMessage;
    
    // 删除标志，默认0，表示未删除；1表示删除
    private Integer delFlag;
}

