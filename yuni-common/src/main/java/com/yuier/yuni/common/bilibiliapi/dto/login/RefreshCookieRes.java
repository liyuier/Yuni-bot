package com.yuier.yuni.common.bilibiliapi.dto.login;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yuier.yuni.common.bilibiliapi.dto.AbstractData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: RefreshCookie
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.login
 * @Date 2024/6/2 16:10
 * @description: 刷新 cookie 接口的返回数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RefreshCookieRes extends AbstractData {
    private Integer status;
    private String message;
    private String refreshToken;
}
