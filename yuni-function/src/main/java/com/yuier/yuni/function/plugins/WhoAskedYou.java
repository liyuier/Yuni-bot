package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.plugin.interf.BaseDetectorPlugin;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: WhoAskedYou
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/9/15 12:50
 * @description: 谁问你了？
 */

@Component
//@Plugin(id = "WhoAskedYou", listener = MessageTypeEnum.GROUP)
public class WhoAskedYou implements BaseDetectorPlugin {

    @Autowired
    CallOneBot callOneBot;

    @Override
    public BaseDetectorDefiner detectorDefine() {
        return BaseDetectorDefiner.build()
                .atBot()
                .containKeyWord("谁问你了")
                .setDetectModel(BaseDetectorModelEnum.AND);
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent) {
        MessageChain chain = new MessageChain();
        chain.replyToMessage(messageEvent.getMessageId().toString())
                        .addText("我问我了！");
        callOneBot.sendGroupMessage(new SendGroupMessageDto(
                messageEvent.getGroupId(),
                chain
        ));
        return ResponseResult.okResult();
    }

    @Override
    public String description() {
        return "那我问你，那我问你，谁问你了？";
    }
}
