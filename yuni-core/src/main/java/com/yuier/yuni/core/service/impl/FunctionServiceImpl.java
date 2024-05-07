package com.yuier.yuni.core.service.impl;

import com.yuier.yuni.common.plugin.dto.functionplugin.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.detector.base.BasePluginsDetector;
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
    public ResponseResult initialBaseFunctionPlugins(BaseDetectorPluginsDto baseDetectorPluginsDto) {
        // 重建基础消息链处理器
        coreGlobalData.setBasePluginsDetector(new BasePluginsDetector(baseDetectorPluginsDto));
        log.info("基础消息链处理器插件初始化成功");

        return ResponseResult.okResult();
    }
}
