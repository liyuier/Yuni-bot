package com.yuier.yuni.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * OneBot 上报事件类
 *
 * @author liyuier
 * @since 2024-04-08 01:38:57
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneBotPostEventDto {

    // 收到消息的时间戳
    private Date time;

    // 收到消息的机器人QQ号
    private Long self_id;

    // 事件类型
    private String post_type;

    // 其余参数
    Map<String, Object> eventData = new HashMap<>();

    @JsonAnySetter
    public void setAdditionalProperty(String key, Object value) {
        this.eventData.put(key, value);
    }
}
