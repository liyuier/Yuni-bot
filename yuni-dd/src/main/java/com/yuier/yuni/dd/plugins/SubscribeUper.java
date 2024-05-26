package com.yuier.yuni.dd.plugins;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.bilibiliapi.CallBilibili;
import com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo.UserCardInfo;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.YuniOrderOptionalArg;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.detector.order.matchedout.OrderOptionMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.domain.message.dto.SendPrivateMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.dd.domain.entity.SubUperEntity;
import com.yuier.yuni.dd.service.SubUperService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: SubscribeUper
 * @Author yuier
 * @Package com.yuier.yuni.dd.plugins
 * @Date 2024/5/14 23:08
 * @description: 订阅B站主播插件
 */
@Component
@Plugin(id = "SubscribeUper", listener = MessageTypeEnum.ALL)
public class SubscribeUper implements YuniOrderPlugin {

    @Autowired
    CallBilibili callBilibili;

    @Autowired
    SubUperService subUperService;

    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;

    private String argUid = "uid";
    private String optionCheck = "check";
    private String optionArgUidOrName = "uidOrName";

    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("订阅")
                .addOptionalArg(argUid, YuniOrderArgContentTypeEnum.NUMBER)
                .addOption(optionCheck, "-check",
                        new YuniOrderOptionalArg(optionArgUidOrName, YuniOrderArgContentTypeEnum.TEXT));
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order) {
        if (order.argExists(argUid)) {
            subscribeUp(messageEvent, order);
        }
        if (order.optionExists(optionCheck)) {
            checkSubscribe(messageEvent, order);
        }

        return ResponseResult.okResult();
    }

    private void subscribeUp(MessageEvent messageEvent, OrderMatchedOut order) {
        Long uidForSub = order.getArgByName(argUid).asLong();
        UserCardInfo userCardInfo = callBilibili.getUserCard(uidForSub);
        String res = "";
        if (null == userCardInfo) {
            res = "UID " + uidForSub + " 无法查找到用户，请检查！";
        } else {
            SubUperEntity subUperEntity = new SubUperEntity();
            if (!targetHasSubedUP(messageEvent, userCardInfo)) {
                subUperEntity = setSubUperEntity(messageEvent, userCardInfo);
                subUperService.save(subUperEntity);
            }
            res = String.format("""
                功能开发中...
                已订阅 UP 主
                昵称：%s
                UID：%d
                """, userCardInfo.getCard().getName(), userCardInfo.getCard().getMid());
        }
        if (messageEvent.isGroupMessage()) {
            callOneBot.sendGroupMessage(new SendGroupMessageDto(
                    messageEvent.getGroupId(),
                    messageChainService.buildChain(res)
            ));
        } else {
            callOneBot.sendPrivateMessage(new SendPrivateMessageDto(
                    messageEvent.getUserId(),
                    messageChainService.buildChain(res)
            ));
        }
    }

    private void checkSubscribe(MessageEvent messageEvent, OrderMatchedOut order) {
        OrderOptionMatchedOut check = order.getOptionByName(optionCheck);
        StringBuilder res = new StringBuilder("功能开发中...\n");
        if (check.argExists(optionArgUidOrName)) {
            String uidOrName = check.getArgByName(optionArgUidOrName).asText();
            LambdaQueryWrapper<SubUperEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SubUperEntity::getSuberId, messageEvent.getPosition())
                    .and(wrapper -> wrapper.eq(SubUperEntity::getUperId, uidOrName)
                            .or().eq(SubUperEntity::getUperName, uidOrName));
            SubUperEntity subUper = subUperService.getOne(queryWrapper);
            res.append(String.format("""
                    本聊天订阅 UP 主:
                    -------------
                    昵称：%s
                    UID：%d
                    """, subUper.getUperName(), subUper.getUperId()));
        } else {
            res.append("本聊天订阅 UP 主：\n");
            LambdaQueryWrapper<SubUperEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SubUperEntity::getSuberId, messageEvent.getPosition());
            List<SubUperEntity> subUperList = subUperService.list(queryWrapper);
            for (SubUperEntity subUper : subUperList) {
                res.append(String.format("""
                        昵称：%s
                        UID：%d
                        """, subUper.getUperName(), subUper.getUperId()));
            }
        }
        if (messageEvent.isGroupMessage()) {
            callOneBot.sendGroupMessage(new SendGroupMessageDto(
                    messageEvent.getGroupId(),
                    messageChainService.buildChain(res.toString())
            ));
        } else {
            callOneBot.sendPrivateMessage(new SendPrivateMessageDto(
                    messageEvent.getUserId(),
                    messageChainService.buildChain(res.toString())
            ));
        }
    }

    private Boolean targetHasSubedUP(MessageEvent messageEvent, UserCardInfo userCardInfo) {
        Long target = messageEvent.isGroupMessage() ? messageEvent.getGroupId() : messageEvent.getUserId();
        LambdaQueryWrapper<SubUperEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubUperEntity::getSuberId, target);
        queryWrapper.eq(SubUperEntity::getUperId, userCardInfo.getCard().getMid());
        List<SubUperEntity> list = subUperService.list(queryWrapper);
        return !list.isEmpty();
    }

    private SubUperEntity setSubUperEntity(MessageEvent messageEvent, UserCardInfo userCardInfo) {
        SubUperEntity subUperEntity = new SubUperEntity();
        subUperEntity.setSubPosition(messageEvent.getMessageType());
        if (messageEvent.isPrivateMessage()) {
            subUperEntity.setSuberId(messageEvent.getUserId());
        } else {
            subUperEntity.setSuberId(messageEvent.getGroupId());
        }
        subUperEntity.setUperId(userCardInfo.getCard().getMid());
        subUperEntity.setUperName(userCardInfo.getCard().getName());
        return subUperEntity;
    }

    @Override
    public String description() {
        return "订阅B站主播";
    }
}
