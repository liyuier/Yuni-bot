package com.yuier.yuni.core.domain.message.nodemessage;

import com.yuier.yuni.core.domain.message.MessageSeg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Title: NodeMessageData
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.message
 * @Date 2024/4/10 23:02
 * @description: 合并转发自定义结点消息段 data 字段
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeMessageData {

    private Long userId;

    private String nickname;

    private List<MessageSeg> content;
}
