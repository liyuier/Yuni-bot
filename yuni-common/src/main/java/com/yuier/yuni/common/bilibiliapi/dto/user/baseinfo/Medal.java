package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: Medal
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/24 23:48
 * @description: 傻逼结构，又嵌套一层勋章信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Medal {
    // 此用户 mid
    private Long uid;
    // 粉丝勋章所属 UP 的 mid
    private Long targetId;
    // 粉丝勋章 id
    private Long medalId;
    // 粉丝勋章等级
    private Integer level;
    // 粉丝勋章名称
    private String medalName;
    // 颜色
    private Long medalColor;
    // 当前亲密度
    private Integer intimacy;
    // 下一等级所需亲密度
    private Integer nextIntimacy;
    // 每日亲密度获取上限
    private Integer dayLimit;
    // 今日已获得亲密度
    private Integer todayFeed;
    // 粉丝勋章颜色 十进制数，可转为十六进制颜色代码
    private Integer medalColorStart;
    // 粉丝勋章颜色 十进制数，可转为十六进制颜色代码
    private Integer medalColorEnd;
    // 粉丝勋章颜色 十进制数，可转为十六进制颜色代码
    private Integer medalColorBorder;
    private Integer isLighted;
    private Integer lightStatus;
    // 当前是否佩戴；0未佩戴，1佩戴
    private Integer wearingStatus;
    private Integer score;
}
