package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.plugins.YuniPlugin;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Title: YuniAtPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.at
 * @Date 2024/4/16 20:02
 * @description: 以 at 触发的插件接口
 */
public interface YuniAtPlugin extends YuniPlugin {
    ResponseResult<T> run(MessageEvent messageEvent);
}
