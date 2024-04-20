package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import com.yuier.yuni.common.enums.MsgDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: NodeData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/14 22:29
 * @description: 合并转发消息段（发）data 类
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@MessageDataEntity(dataType = MsgDataEnum.NODE)
public class NodeData extends MessageData {
    // 转发的消息 ID
    private String id;

    @Override
    public String toString() {
        return "【合并转发消息】";
    }
}
