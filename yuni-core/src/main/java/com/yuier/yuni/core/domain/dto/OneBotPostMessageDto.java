package com.yuier.yuni.core.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * OneBot 上报消息类
 *
 * @author liyuier
 * @since 2024-04-08 01:38:57
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneBotPostMessageDto {

    // 消息类型
    private String type;

    // 消息数据
    private OneBotPostMessageDataDto data;
}
