package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.enums.MessageDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectFullMatchTextForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/7 22:44
 * @description: 实际使用的消息链是否全匹配字符串探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectFullMatchTextForUse implements BaseSubDetectorForUse{

    private ArrayList<String> fullTexts;

    @Override
    public Boolean hit(MessageChain chain) {
        ArrayList<MessageSeg> content = chain.getContent();
        if (content.size() > 1) {
            return false;
        }
        if (!content.get(SystemConstants.FIRST_INDEX).typeOf(MessageDataEnum.TEXT)) {
            return false;
        }
        String messageFullStr = ((TextData) content.get(SystemConstants.FIRST_INDEX).getData()).getText();
        for (String text : fullTexts) {
            if (messageFullStr.equals(text)) {
                return true;
            }
        }
        return false;
    }
}
