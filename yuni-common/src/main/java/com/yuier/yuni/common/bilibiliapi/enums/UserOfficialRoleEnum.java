package com.yuier.yuni.common.bilibiliapi.enums;

import lombok.Getter;

/**
 * @Title: UserOfficialInfo
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.enums
 * @Date 2024/5/25 0:00
 * @description: 用户认证类型
 */
@Getter
public enum UserOfficialRoleEnum {
    NONE(0, "无", "无"),
    FAMOUS_UP(1, "个人认证", "知名UP主"),
    BIG_V(2, "个人认证", "大V达人"),
    ENTERPRISE(3, "机构认证", "企业"),
    ORGANIZATION(4, "机构认证", "组织"),
    MEDIA(5, "机构认证", "媒体"),
    GOVERNMENT(6, "机构认证", "政府"),
    HIGH_ENERGY_ANCHOR(7, "个人认证", "高能主播"),
    FAMOUS_SOCIAL_PERSON(8, "个人认证", "社会知名人士");


    private final Integer id;
    private final String officialRole;
    private final String detailRole;

    UserOfficialRoleEnum(Integer id, String officialRole, String detailRole) {
        this.id = id;
        this.officialRole = officialRole;
        this.detailRole = detailRole;
    }
}
