package com.yuier.yuni.function.service.impl;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.annotation.function.OrderCallFunction;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.detector.MessageDetectorDefiner;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.function.base.BaseDetectorPluginDto;
import com.yuier.yuni.common.domain.message.dto.function.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.enums.FuncBaseCallerEnum;
import com.yuier.yuni.common.domain.message.dto.function.FunctionPluginDto;
import com.yuier.yuni.common.domain.message.dto.function.FunctionPluginsDto;
import com.yuier.yuni.common.service.YuniHttpService;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import com.yuier.yuni.common.utils.CallCore;
import com.yuier.yuni.function.domain.global.FunctionGlobalData;
import com.yuier.yuni.function.domain.plugin.FunctionPlugin;
import com.yuier.yuni.function.domain.plugin.FunctionPlugins;
import com.yuier.yuni.function.domain.plugin.base.BaseDetectorPlugin;
import com.yuier.yuni.function.plugins.interf.YuniOrderPlugin;
import com.yuier.yuni.function.service.FunctionPluginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: PluginServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.function.service.impl
 * @Date 2024/4/16 20:30
 * @description: 插件服务接口实现类
 */
@Slf4j
@Service
public class FunctionPluginServiceImpl implements FunctionPluginService {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    FunctionGlobalData functionGlobalData;
    @Autowired
    CallCore callCore;

    @Override
    public PluginFunctionDto buildPluginFunctionDto() {
        HashMap<String, ArrayList<String>> functionMap = new HashMap<>();
        ArrayList<String> orders = getFunctionOrderNames();
        functionMap.put(FuncBaseCallerEnum.ORDER.toString(), orders);

        return new PluginFunctionDto(functionMap);
    }

    @Override
    public void scanAndBuildPlugin() {
        FunctionPlugins functionPlugins = new FunctionPlugins();
        functionGlobalData.setPlugins(functionPlugins);
        loadPlugins(functionPlugins);
        initialPluginsToCore(functionPlugins);
    }

    private void loadPlugins(FunctionPlugins functionPlugins) {
        // 扫描加了 Plugin 注解的插件
        Map<String, Object> pluginMap = applicationContext.getBeansWithAnnotation(Plugin.class);
        try {
            // 对每个插件
            for (Object pluginBean : pluginMap.values()) {
                if (pluginBean instanceof YuniOrderPlugin) {
                    continue;
                }
                // 检查合法性
                Plugin pluginAnnotation = pluginBean.getClass().getAnnotation(Plugin.class);
                String pluginId = pluginAnnotation.id();
                if (functionPlugins.getPluginMap().containsKey(pluginId)) {
                    log.error("插件" + pluginBean + "的 ID " + pluginId + " 与其他插件重复，请检查！");
                    continue;
                }
                // detectorDefine 方法
                Method detectorDefineMethod = pluginBean.getClass().getDeclaredMethod(SystemConstants.PLUGIN_CRITICAL_NAME.DETECTOR_DEFINE);
                // description 方法
                Method descriptionMethod = pluginBean.getClass().getDeclaredMethod(SystemConstants.PLUGIN_CRITICAL_NAME.DESCRIPTION);
                // run 方法
                Method runMethod = pluginBean.getClass().getDeclaredMethod(SystemConstants.PLUGIN_CRITICAL_NAME.FUNC_PLUGIN_ENTRY, MessageEvent.class);

                // 创建 plugin 实例
                FunctionPlugin funcPlugin = new FunctionPlugin();
                funcPlugin.setPluginId(pluginId);
                funcPlugin.setPluginBean(pluginBean);
                funcPlugin.setDescription((String) descriptionMethod.invoke(pluginBean));
                funcPlugin.setListener(pluginAnnotation.listener());
                funcPlugin.setRunMethod(runMethod);
                MessageDetectorDefiner detectorDefiner = (MessageDetectorDefiner) detectorDefineMethod.invoke(pluginBean);
                if (!detectorDefiner.defineValid()) {
                    log.error("插件 " + pluginBean + " 的消息链探测器定义无效！请检查。");
                    continue;
                }
                funcPlugin.setDetectorDefiner(detectorDefiner);
                functionPlugins.getPluginMap().put(pluginId, funcPlugin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("扫描完毕");
    }

    private void initialPluginsToCore(FunctionPlugins functionPlugins) {
        FunctionPluginsDto functionPluginsDto = new FunctionPluginsDto();
        BaseDetectorPluginsDto baseDetectorPluginsDto = new BaseDetectorPluginsDto();
        for (FunctionPlugin plugin : functionPlugins.getPluginMap().values()) {
            FunctionPluginDto functionPluginDto = new FunctionPluginDto();
            functionPluginDto.setPluginId(plugin.getPluginId());
            functionPluginDto.setMessageDetectorDefinerDto(plugin.getDetectorDefiner().toDto());
            functionPluginsDto.getPluginDtoMap().put(functionPluginDto.getPluginId(), functionPluginDto);

            // 如果插件使用了基础消息链探测器
            if (plugin.useDetector(BaseDetectorDefiner.class)) {
                BaseDetectorPluginDto baseDetectorPluginDto = new BaseDetectorPluginDto();
                baseDetectorPluginDto.buildFromFunctionPluginDto(functionPluginDto);
                baseDetectorPluginsDto.getPluginDtoMap().put(baseDetectorPluginDto.getPluginId(), baseDetectorPluginDto);
            }
        }
        callCore.initialBaseDetectorPluginToCore(baseDetectorPluginsDto);
    }

    private ArrayList<String> getFunctionOrderNames() {
        ArrayList<String> orders = new ArrayList<>();
        Map<String, Object> orderCallFunctionBeans = applicationContext.getBeansWithAnnotation(OrderCallFunction.class);
        for (Object bean : orderCallFunctionBeans.values()) {
            OrderCallFunction annotation = bean.getClass().getAnnotation(OrderCallFunction.class);
            orders.add(annotation.orderWord());
        }
        return orders;
    }
}
