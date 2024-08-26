package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.YuniOrderOptionalArg;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.YuniHttpService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: OrderTest
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/5/12 17:45
 * @description: 指令测试插件，无实际意义
 */
@Component
@Plugin(id = "OrderTest", listener = MessageTypeEnum.GROUP)
public class OrderTest implements YuniOrderPlugin {

    @Autowired
    YuniHttpService yuniHttpService;
    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;

    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("test")
                .addOptionalArg("dailyNews", YuniOrderArgContentTypeEnum.TEXT);
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order) {
        return ResponseResult.okResult();
    }

    @Override
    public String description() {
        return "指令系统测试插件，并无实际意义";
    }
}
