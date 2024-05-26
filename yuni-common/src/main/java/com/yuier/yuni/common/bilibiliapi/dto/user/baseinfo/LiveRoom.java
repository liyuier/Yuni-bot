package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: LiveRoom
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo
 * @Date 2024/5/25 0:26
 * @description: 直播间信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LiveRoom {
    /**
     * 直播间状态
     * 0：无房间
     * 1：有房间
     */
    private Integer roomStatus;
    /**
     * 直播状态
     * 0：未开播
     * 1：直播中
     */
    private Integer liveStatus;
    // 直播间网页 url
    private String url;
    // 直播间标题
    private String title;
    // 直播间封面 url
    private String cover;
    // 观看人数信息
    private WatchedShow watchedShow;
}
