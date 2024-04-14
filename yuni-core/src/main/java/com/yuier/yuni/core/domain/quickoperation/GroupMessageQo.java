package com.yuier.yuni.core.domain.quickoperation;

import com.yuier.yuni.common.domain.message.MessageSeg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Title: GroupMessageQo
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.quickoperation
 * @Date 2024/4/10 21:30
 * @description: 群聊消息快速操作
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageQo {

    /**
     * 要回复的内容
     */
    private List<MessageSeg> reply;

    /**
     * 消息是否作为纯文本发送（即不解析CQ码）
     * 只在 reply 是字符串时有效
     */
    private boolean autoEscape;

    /**
     * 是否要在回复开头 at 发送者（自动添加）
     * 发送者是匿名用户时无效
     */
    private boolean atSender;

    /**
     * 撤回该条消息
     */
    private boolean delete;

    /**
     * 把发送者踢出群组（需要登录号权限足够）
     * 不拒绝此人后续加群请求
     * 发送者是匿名用户时无效
     */
    private boolean kick;

    /**
     * 把发送者禁言 ban_duration 指定时长
     * 对匿名用户也有效
     */
    private boolean ban;

    // 禁言时长
    private int banDuration;

}
