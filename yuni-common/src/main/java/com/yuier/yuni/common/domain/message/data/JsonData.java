package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import com.yuier.yuni.common.enums.MsgDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: JsonData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/14 22:37
 * @description: Json 消息段 data 类
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@MessageDataEntity(dataType = MsgDataEnum.JSON)
public class JsonData extends MessageData {
    private String data;

    @Override
    public String toString() {
        return "[JSON消息<" + this.data + ">]";
    }
}
