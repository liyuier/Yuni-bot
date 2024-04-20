package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import com.yuier.yuni.common.enums.MsgDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: MarkdownData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/20 17:50
 * @description: markdown 消息 data 字段
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@MessageDataEntity(dataType = MsgDataEnum.MARKDOWN)
public class MarkdownData extends MessageData {
    private String data;

    @Override
    public String toString() {
        return "[markdown消息<#" + this.data + ">]";
    }
}
