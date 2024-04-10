package com.yuier.yuni.core.domain.quickoperation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: GroupRequestQo
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.quickoperation
 * @Date 2024/4/11 1:42
 * @description: 加群请求 / 邀请快速响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequestQo {

    // 是否同意加群请求
    private boolean approve;

    // 拒绝理由（仅在拒绝时有效）
    private String reason;
}
