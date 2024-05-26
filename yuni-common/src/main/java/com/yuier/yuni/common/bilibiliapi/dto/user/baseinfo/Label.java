package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: Label
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/25 0:13
 * @description: 会员标签
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Label {
    // 空 作用尚不明确
    private String path;
    /**
     * 会员类型文案 大会员 年度大会员 十年大会员 百年大会员 最强绿鲤鱼
     */
    private String text;
    /**
     * 会员标签
     * vip：大会员
     * annual_vip：年度大会员
     * ten_annual_vip：十年大会员
     * hundred_annual_vip：百年大会员
     * fools_day_hundred_annual_vip：最强绿鲤鱼
     */
    private String labelTheme;
}
