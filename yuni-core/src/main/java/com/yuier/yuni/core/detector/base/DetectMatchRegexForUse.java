package com.yuier.yuni.core.detector.base;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.enums.MessageDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: DetectMatchRegexForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:49
 * @description: 实际使用的正则表达式探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectMatchRegexForUse implements BaseSubDetectorForUse{
    private String regex;

    @Override
    public Boolean hit(MessageChain chain) {
        for (MessageSeg messageSeg : chain.getContent()) {
            if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                TextData data = (TextData) messageSeg.getData();
                if (data.getText().matches(regex)) {
                    return true;
                }
            }
        }
        return false;
    }
}
