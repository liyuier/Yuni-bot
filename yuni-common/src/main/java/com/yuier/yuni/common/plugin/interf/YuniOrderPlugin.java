package com.yuier.yuni.common.plugin.interf;

import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Title: YuniOrderPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.order
 * @Date 2024/4/16 20:22
 * @description: 以指令触发的功能插件接口
 */
public interface YuniOrderPlugin extends YuniPlugin {

    // 插件应用的消息探测器
    YuniOrderDefiner detectorDefine();

    // 插件入口
    ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order);
}
