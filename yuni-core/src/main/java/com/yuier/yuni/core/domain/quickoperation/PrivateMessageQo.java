package com.yuier.yuni.core.domain.quickoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuier.yuni.core.domain.message.MessageSeg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Title: PrivateMessageQo
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.quickoperation
 * @Date 2024/4/10 21:18
 * @description: 私聊消息快速操作
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateMessageQo {

    // 要回复的内容
    private List<MessageSeg> reply;

    // 消息是否作为纯文本发送（即不解析CQ码）
    // 只在 reply 是字符串时有效
    @JsonProperty("auto_escape")
    private boolean autoEscape;
}
