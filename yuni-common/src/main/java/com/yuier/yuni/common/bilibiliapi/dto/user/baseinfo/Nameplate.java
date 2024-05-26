package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: Nameplate
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/25 0:17
 * @description: 勋章信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Nameplate {
    // 勋章 id
    private Integer nid;
    // 勋章名称
    private String name;
    // 勋章图标
    private String image;
    // 勋章图标（小）
    private String imageSmall;
    // 勋章等级
    private String level;
    // 获取条件
    private String condition;
    /**
     * 是否关注此用户
     * true：已关注
     * false：未关注
     * 需要登录（Cookie）
     * 未登录恒为false
     */
    private Boolean isFollowed;
    // 主页头图链接
    private String topPhoto;
}
