package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.plugins.YuniPlugin;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Title: YuniOrderPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.order
 * @Date 2024/4/16 20:22
 * @description: 以指令触发的功能插件接口
 */
public interface YuniOrderPlugin extends YuniPlugin {
    ResponseResult<T> run(MessageEvent messageEvent);
}
