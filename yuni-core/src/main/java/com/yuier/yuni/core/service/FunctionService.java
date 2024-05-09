package com.yuier.yuni.core.service;

import com.yuier.yuni.common.plugin.dto.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.plugin.dto.positive.PositivePluginsDto;
import com.yuier.yuni.common.utils.ResponseResult;

/**
 * @Title: FunctionService
 * @Author yuier
 * @Package com.yuier.yuni.core.service
 * @Date 2024/4/16 21:05
 * @description: function 相关服务接口
 */
public interface FunctionService {

    ResponseResult initialBaseFunctionPlugins(BaseDetectorPluginsDto baseDetectorPluginsDto);

    ResponseResult initialPositiveFunctionPlugins(PositivePluginsDto positivePluginDto);
}
