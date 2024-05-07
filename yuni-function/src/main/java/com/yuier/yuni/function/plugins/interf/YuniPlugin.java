package com.yuier.yuni.function.plugins.interf;

import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Title: YuniPlugin
 * @Author yuier
 * @Package com.yuier.yuni.function
 * @Date 2024/4/16 19:36
 * @description: Yuni 机器人功能插件接口
 */
public interface YuniPlugin {

    // 插件描述
    String description();

    // 插件入口
    ResponseResult<T> run(MessageEvent messageEvent);

}
