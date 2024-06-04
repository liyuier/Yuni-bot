package com.yuier.yuni.common.bilibiliapi.dto.login;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yuier.yuni.common.bilibiliapi.dto.AbstractData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: CookieInfo
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.login
 * @Date 2024/6/2 3:17
 * @description: 检查 cookie 是否需要刷新响应数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CookieInfo extends AbstractData {
    // 判断是否应该刷新 cookie
    private Boolean refresh;
    // 当前毫秒的时间戳
    private Long timestamp;
}
