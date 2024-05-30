package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.data.AtData;
import com.yuier.yuni.common.domain.message.dto.GetGroupMemberListDto;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.domain.message.res.GetGroupMemberListRes;
import com.yuier.yuni.common.domain.message.res.data.GetGroupMemberInfoResData;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.enums.UserRoleEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: DeclareWar
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/5/30 22:35
 * @description: 向万国宣战
 */
@Component
@Plugin(id = "DeclareWar", listener = MessageTypeEnum.GROUP)
public class DeclareWar implements YuniOrderPlugin{

    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;


    private String target = "target";

    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("宣战")
                .addOptionalArg(target, YuniOrderArgContentTypeEnum.AT);
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order) {
        MessageChain chain = messageChainService.buildChain("");
        if (order.argExists(target)) {
            chain.addText("向 ")
                    .addAt(new AtData(order.getArgByName(target).asAt().getQq()))
                    .addText(" 宣战！");
        } else {
            GetGroupMemberListRes groupMemberList = callOneBot.getGroupMemberList(new GetGroupMemberListDto(messageEvent.getGroupId()));
            for (GetGroupMemberInfoResData memberData : groupMemberList.getData()) {
                String role = memberData.getRole();
                if (role.equals(UserRoleEnum.OWNER.toString()) || role.equals(UserRoleEnum.ADMIN.toString())) {
                    chain.addText("向 ")
                            .addAt(new AtData(memberData.getUserId().toString()))
                            .addText(" 宣战！\n");

                }
            }
            String text = chain.lastTextData().getText();
            chain.lastTextData().setText(text.substring(0, text.length() - 1));
        }
        callOneBot.sendGroupMessage(new SendGroupMessageDto(
                messageEvent.getGroupId(),
                chain
        ));
        return ResponseResult.okResult();
    }

    @Override
    public String description() {
        return "向群友，宣战！";
    }
}
