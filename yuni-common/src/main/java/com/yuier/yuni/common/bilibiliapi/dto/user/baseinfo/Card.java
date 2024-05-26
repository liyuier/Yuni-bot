package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @Title: Card
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/25 0:45
 * @description: 用户卡片信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Card {
    private Long mid;
    private String name;
    private String sex;
    private String face;
    /**
     * 用户状态
     * 0：正常
     * -2：被封禁
     */
    private Integer spacesta;
    // 作用尚不明确
    private String birthday;
    private String place;
    private String description;
    private Integer article;

    // 粉丝数
    private Integer fans;
    // 关注数
    private Integer friend;
    // 关注数
    private Integer attention;
    // 签名
    private String sign;
    // 等级
    private LevelInfo levelInfo;
    // 勋章
    private Nameplate nameplate;
    // 认证信息
    @JsonProperty("Official")
    private OfficialInfo Official;
    private VIP vip;
    // 主页头图
    private Space space;
}
