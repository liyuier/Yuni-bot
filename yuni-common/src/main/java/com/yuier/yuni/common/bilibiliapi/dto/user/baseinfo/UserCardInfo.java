package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yuier.yuni.common.bilibiliapi.dto.AbstractData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: UserCardInfo
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/25 0:44
 * @description: 用户名片信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserCardInfo extends AbstractData {
    // 卡片信息
    private Card card;
    // 是否关注此用户
    private Boolean following;
    // 用户稿件数
    private Integer archiveCount;
    // 作用尚不明确
    private Integer articleCount;
    // 粉丝数
    private Integer follower;
    // 点赞数
    private Integer likeNum;
}
