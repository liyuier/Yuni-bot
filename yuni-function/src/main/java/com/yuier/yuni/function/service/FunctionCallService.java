package com.yuier.yuni.function.service;

import com.yuier.yuni.common.domain.dto.CallBaseFunctionPluginDto;
import com.yuier.yuni.common.utils.ResponseResult;
/**
 * @Title: FunctionCallService
 * @Author yuier
 * @Package com.yuier.yuni.function.service
 * @Date 2024/4/16 20:29
 * @description: 功能调用服务接口
 */
public interface FunctionCallService {

    ResponseResult callPlugin(CallBaseFunctionPluginDto callBaseFunctionPluginDto);
}
