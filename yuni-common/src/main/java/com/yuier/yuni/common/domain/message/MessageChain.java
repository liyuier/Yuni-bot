package com.yuier.yuni.common.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: MessageChain
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.message
 * @Date 2024/4/14 3:21
 * @description: 消息链实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageChain {

    ArrayList<MessageSeg> content;
}
