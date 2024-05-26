package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: VIP
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/25 0:10
 * @description: 会员信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VIP {
    /**
     * 会员类型
     * 0：无
     * 1：月大会员
     * 2：年度及以上大会员
     */
    private Integer type;
    /**
     * 会员状态
     * 0：无
     * 1：有
     */
    private Integer status;
    // 会员过期时间 毫秒时间戳
    private Long dueDate;
    /**
     * 支付类型
     * 0：未支付（常见于官方账号）
     * 1：已支付（以正常渠道获取的大会员均为此值）
     */
    private Integer vipPayType;
    // 作用尚不明确
    private Integer themeType;
    // 会员标签
    private Label label;
    /**
     * 大角色类型	1：月度大会员
     * 3：年度大会员
     * 7：十年大会员
     * 15：百年大会员
     */
    private Integer role;
}
