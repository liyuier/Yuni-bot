package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: ContactData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/14 22:11
 * @description: 推荐好友/群消息段 data 数据
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@MessageDataEntity(messageType = "contact")
public class ContactData extends MessageData {
    // 推荐好友，可选值： qq/group
    private String type;
    // 被推荐人/群的 QQ 号
    private String id;
}