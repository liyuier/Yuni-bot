package com.yuier.yuni.core.service.impl;

import com.yuier.yuni.common.plugin.dto.function.base.BaseDetectorPluginDto;
import com.yuier.yuni.common.plugin.dto.function.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.plugin.dto.function.positive.PositivePluginsDto;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.global.detector.PluginForDetector;
import com.yuier.yuni.core.domain.global.detector.base.BasePluginForDetector;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.service.FunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap<String, PluginForDetector> pluginMap = coreGlobalData.getPluginsForDetect().getPluginMap();
        HashMap<String, BaseDetectorPluginDto> pluginDtoMap = baseDetectorPluginsDto.getPluginDtoMap();
        for (String pluginId : pluginDtoMap.keySet()) {
            pluginMap.put(pluginId, new BasePluginForDetector(pluginDtoMap.get(pluginId)));
        }
        log.info("基础消息链处理器插件初始化成功");

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult initialPositiveFunctionPlugins(PositivePluginsDto positivePluginDto) {
        coreGlobalData.getPositivePlugins().setPositivePluginIdList(positivePluginDto.getPositivePluginIdList());
        log.info("主动触发插件初始化成功");
        return ResponseResult.okResult();
    }
}
