package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.plugins.YuniPlugin;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Title: YuniKeyWordPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.keyword
 * @Date 2024/4/16 20:21
 * @description: 以关键词触发的功能插件接口
 */
public interface YuniKeyWordPlugin extends YuniPlugin {
    ResponseResult<T> run(MessageEvent messageEvent);
}
