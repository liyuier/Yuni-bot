package com.yuier.yuni.core.service.impl;

import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.service.FunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: FunctionServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.core.service.impl
 * @Date 2024/4/16 21:06
 * @description: function 相关服务实现类
 */
@Slf4j
@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    CoreGlobalData coreGlobalData;

    @Override
    public ResponseResult initializeFunctions(PluginFunctionDto pluginFunctionDto) {
        if (null != coreGlobalData.getFunctions()) {
            coreGlobalData.getFunctions().clear();
        }
        coreGlobalData.setFunctions(pluginFunctionDto.getFunctionMap());
        log.info("功能初始化成功");
        return ResponseResult.okResult();
    }
}
