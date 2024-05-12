package com.yuier.yuni.common.domain.message;

import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.data.AtData;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.enums.MessageDataEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: MessageChain
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.message
 * @Date 2024/4/14 3:21
 * @description: 消息链实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageChain {

    private ArrayList<MessageSeg> content;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (MessageSeg seg : content) {
            str.append(seg.getData().toString());
        }
        return str.toString();
    }

    public Boolean atUser(Long userId) {
        for (MessageSeg messageSeg : content) {
            if (messageSeg.typeOf(MessageDataEnum.AT)) {
                AtData data = (AtData) messageSeg.getData();
                if (data.getQq().equals(userId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean atUser() {
        for (MessageSeg messageSeg : content) {
            if (messageSeg.typeOf(MessageDataEnum.AT)) {
                return true;
            }
        }
        return false;
    }

    public Boolean containText(String str) {
        for (MessageSeg messageSeg : content) {
            if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                TextData data = (TextData) messageSeg.getData();
                if (data.getText().contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean onlyTextData() {
        for (MessageSeg messageSeg : content) {
            if (!messageSeg.typeOf(MessageDataEnum.TEXT)) {
                return false;
            }
        }
        return true;
    }

    public Boolean startWithTextData() {
        return content.get(SystemConstants.FIRST_INDEX).typeOf(MessageDataEnum.TEXT) &&
                !((TextData) content.get(SystemConstants.FIRST_INDEX).getData()).getText().trim().isEmpty();
    }

    public Boolean startWithReplyData() {
        return content.get(SystemConstants.FIRST_INDEX).typeOf(MessageDataEnum.REPLY);
    }

    public Boolean startWithTextDataFollowingReplyData() {
        if (startWithReplyData()) {
            if (content.get(SystemConstants.FIRST_INDEX + 1).typeOf(MessageDataEnum.TEXT)) {
                if (!((TextData) content.get(SystemConstants.FIRST_INDEX + 1).getData()).getText().trim().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public TextData firstTextData() {
        for (MessageSeg messageSeg : content) {
            if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                return (TextData) messageSeg.getData();
            }
        }
        return null;
    }

    public TextData lastTextData() {
        for (int i = content.size() - 1; i >= 0; i --) {
            MessageSeg messageSeg = content.get(i);
            if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                return (TextData) messageSeg.getData();
            }
        }
        return null;
    }
}
