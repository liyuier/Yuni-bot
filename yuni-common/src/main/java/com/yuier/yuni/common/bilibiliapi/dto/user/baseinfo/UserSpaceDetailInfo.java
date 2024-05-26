package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yuier.yuni.common.bilibiliapi.dto.AbstractData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: UserSpaceDetailInfo
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/24 23:42
 * @description: 用户空间详细信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserSpaceDetailInfo extends AbstractData {
    private Long mid;
    private String name;
    private String sex;
    private String face;
    // 签名
    private String sign;
    // 是否具有粉丝勋章
    private Boolean fansBadge;
    // 粉丝勋章信息
    private FansMedal fansMedal;
    // 认证信息
    private OfficialInfo official;
    // 会员信息
    private VIP vip;
    // 勋章信息
    private Nameplate nameplate;
    // 系统通知，如纪念账号等
    private SysNotice sysNotice;
    // 直播间信息
    private LiveRoom liveRoom;
    // 生日 MM-DD
    private String birthday;
    // 学校
    private School school;
    // 专业资质信息
    private Profession profession;
    // 是否为硬核会员
    private Boolean isSeniorMember;

}
