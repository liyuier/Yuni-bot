package com.yuier.yuni.core.service.impl;

import com.yuier.yuni.common.plugin.dto.base.BaseDetectorPluginDto;
import com.yuier.yuni.common.plugin.dto.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.plugin.dto.order.OrderDetectorPluginDto;
import com.yuier.yuni.common.plugin.dto.order.OrderDetectorPluginsDto;
import com.yuier.yuni.common.plugin.dto.positive.PositivePluginsDto;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.global.detector.PluginForDetect;
import com.yuier.yuni.core.domain.global.detector.base.BasePluginForDetect;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.domain.global.detector.order.OrderPluginForDetect;
import com.yuier.yuni.core.service.FunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        HashMap<String, PluginForDetect> pluginMap = coreGlobalData.getPluginsForDetect().getPluginMap();
        HashMap<String, BaseDetectorPluginDto> pluginDtoMap = baseDetectorPluginsDto.getPluginDtoMap();
        for (String pluginId : pluginDtoMap.keySet()) {
            pluginMap.put(pluginId, new BasePluginForDetect(pluginDtoMap.get(pluginId)));
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

    @Override
    public ResponseResult initialOrderFunctionPlugins(OrderDetectorPluginsDto orderDetectorPluginsDto) {
        // 重建指令探测器
        HashMap<String, PluginForDetect> pluginMap = coreGlobalData.getPluginsForDetect().getPluginMap();
        HashMap<String, OrderDetectorPluginDto> pluginDtoMap = orderDetectorPluginsDto.getPluginDtoMap();
        for (String pluginId : pluginDtoMap.keySet()) {
            pluginMap.put(pluginId, new OrderPluginForDetect(pluginDtoMap.get(pluginId)));
        }
        return ResponseResult.okResult();
    }
}
