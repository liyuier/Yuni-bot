package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: MarketFaceData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/20 17:46
 * @description: 商城表情 data 类
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@MessageDataEntity(messageType = "mface")
public class MarketFaceData extends MessageData {
    private String text;

    @Override
    public String toString() {
        return "【商城表情<#" + this.text + ">】";
    }
}