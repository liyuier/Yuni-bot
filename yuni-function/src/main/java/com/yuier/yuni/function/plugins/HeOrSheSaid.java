package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.enums.MessageDataEnum;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.plugins.interf.BaseDetectorPlugin;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: HeOrSheSaid
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/5/5 3:58
 * @description: 他/她说
 */
@Component
@Plugin(id = "HeOrSheSaid", listener = MessageTypeEnum.GROUP)
public class HeOrSheSaid implements BaseDetectorPlugin {

    @Autowired
    CallOneBot callOneBot;

    @Override
    public BaseDetectorDefiner detectorDefine() {
        return BaseDetectorDefiner.build()
                .containKeyWord("我");
    }

    @Override
    public String description() {
        return "将含有“我”字的消息处理一下，然后重复一遍";
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent) {
        String senderName = "";
        if (messageEvent.isAnonymousMessage()) {
            senderName = messageEvent.getSender().getNickname();
        } else if (messageEvent.isGroupMessage()) {
            String groupCard = messageEvent.getSender().getCard();
            senderName =
                    null != groupCard && !groupCard.isEmpty() ?
                        groupCard : messageEvent.getSender().getNickname();
        }
        MessageChain chain = messageEvent.getMessage();
        for (MessageSeg seg : chain.getContent()) {
            if (seg.typeOf(MessageDataEnum.TEXT)) {
                TextData data = (TextData)seg.getData();
                data.setText(data.getText().replace("我", senderName));
            }
        }
        callOneBot.sendGroupMessage(new SendGroupMessageDto(
                messageEvent.getGroupId(),
                chain
        ));
        return ResponseResult.okResult();
    }
}
