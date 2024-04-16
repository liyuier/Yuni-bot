package com.yuier.yuni.core.service.impl;

import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.global.GlobalData;
import com.yuier.yuni.core.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: FunctionServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.core.service.impl
 * @Date 2024/4/16 21:06
 * @description: function 相关服务实现类
 */
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    GlobalData globalData;

    @Override
    public ResponseResult initializeFunctions(PluginFunctionDto pluginFunctionDto) {
        globalData.getFunctions().clear();
        globalData.setFunctions(pluginFunctionDto.getFunctionMap());
        return ResponseResult.okResult();
    }
}
