package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import com.yuier.yuni.common.enums.MsgDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: TextData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/14 21:36
 * @description: 纯文本消息 data 类
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@MessageDataEntity(dataType = MsgDataEnum.TEXT)
public class TextData extends MessageData {
    private String text;

    @Override
    public String toString() {
        return this.text;
    }
}
