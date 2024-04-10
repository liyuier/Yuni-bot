package com.yuier.yuni.core.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: QQUserEntity
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.entity
 * @Date 2024/4/10 21:11
 * @description: QQ 用户实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QQUser {

    private Long userId;

    private String nickname;
}
