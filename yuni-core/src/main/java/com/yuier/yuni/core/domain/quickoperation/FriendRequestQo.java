package com.yuier.yuni.core.domain.quickoperation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: FriendAddRequestQo
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.quickoperation
 * @Date 2024/4/11 1:40
 * @description: 加好友请求快速操作
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestQo {

    // 是否同意加好友请求
    private boolean approve;

    // 添加后好友备注（仅在同意时有效）
    private String remark;
}
