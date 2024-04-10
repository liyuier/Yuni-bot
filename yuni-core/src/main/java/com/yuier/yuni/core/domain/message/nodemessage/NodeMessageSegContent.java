package com.yuier.yuni.core.domain.message.nodemessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

/**
 * @Title: MessageSegVo
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.message
 * @Date 2024/4/10 23:07
 * @description: 转发消息中的 MessageSeg 类，以适配嵌套转发消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeMessageSegContent {
    // 消息段类型
    private String type;

    // 消息段数据
    private Map<String, Objects> data;
}
