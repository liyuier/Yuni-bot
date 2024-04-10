package com.yuier.yuni.core.domain.dto;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OneBotPostEventDto {

    // 收到消息的时间戳
    private Date time;

    // 收到消息的机器人QQ号
    private Long selfId;

    // 事件类型
    private String postType;

    // 其余参数
    private Map<String, Object> eventData = new HashMap<>();

    // 注意，这里的 Map 中的 key 仍然是下划线风格
    @JsonAnySetter
    public void setAdditionalProperty(String key, Object value) {
        this.eventData.put(key, value);
    }
}
