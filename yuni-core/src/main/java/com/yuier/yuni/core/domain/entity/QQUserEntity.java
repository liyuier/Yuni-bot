package com.yuier.yuni.core.domain.entity;

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
public class QQUserEntity {

    private Long userId;

    private String nickname;
}
