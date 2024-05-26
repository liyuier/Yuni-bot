package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: SysNotice
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/25 0:22
 * @description: 系统通知，主要用于展示用户争议、纪念账号等的小黄条
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SysNotice {
    private Integer id;
    private String content;
    private String url;
    private Integer noticeType;
    private String icon;
    private String textColor;
    private String bgColor;
}
