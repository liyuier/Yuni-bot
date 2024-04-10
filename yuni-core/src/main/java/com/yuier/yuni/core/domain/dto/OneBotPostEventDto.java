package com.yuier.yuni.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @JsonProperty("self_id")
    private Long selfId;

    // 事件类型
    @JsonProperty("post_type")
    private String postType;

    // 其余参数
    private Map<String, Object> eventData = new HashMap<>();

    @JsonAnySetter
    public void setAdditionalProperty(String key, Object value) {
        this.eventData.put(key, value);
    }
}
