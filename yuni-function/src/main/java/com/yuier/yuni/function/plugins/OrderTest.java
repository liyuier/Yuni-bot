package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.detector.order.YuniOrderArg;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgRequireTypeEnum;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;
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
    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("/test")
                .addRequiredArg("testStr", YuniOrderArgContentTypeEnum.TEXT)
                .addRequiredArg("testAt", YuniOrderArgContentTypeEnum.AT)
                .addRequiredArg("testNumber", YuniOrderArgContentTypeEnum.NUMBER)
                .addOptionalArg("testImage", YuniOrderArgContentTypeEnum.Image)
                .addOption("testOpt1", "-1", new YuniOrderArg(
                        "opt1Arg1",
                        YuniOrderArgRequireTypeEnum.REQUIRED
                ));
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
