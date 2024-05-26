package com.yuier.yuni.dd.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.bilibiliapi.CallBilibili;
import com.yuier.yuni.common.bilibiliapi.dto.user.baseinfo.UserCardInfo;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.YuniOrderOptionalArg;
import com.yuier.yuni.common.detector.order.matchedout.OrderArgMatchedOut;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("订阅")
                .addOptionalArg("uid", YuniOrderArgContentTypeEnum.NUMBER)
                .addOption("check", "-check",
                        new YuniOrderOptionalArg("uid", YuniOrderArgContentTypeEnum.NUMBER));
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order) {
        Long uidForSub = order.getArgByName("uid").asLong();
        UserCardInfo userCard = callBilibili.getUserCard(uidForSub, true);
        return ResponseResult.okResult();
    }

    @Override
    public String description() {
        return "订阅B站主播";
    }
}
