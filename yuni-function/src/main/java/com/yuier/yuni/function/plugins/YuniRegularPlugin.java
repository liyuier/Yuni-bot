package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Title: YuniRegularPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.regular
 * @Date 2024/4/16 20:24
 * @description: 以命中正则表达式触发的功能插件接口
 */
public interface YuniRegularPlugin extends YuniPlugin {
    ResponseResult<T> run(MessageEvent messageEvent);
}
