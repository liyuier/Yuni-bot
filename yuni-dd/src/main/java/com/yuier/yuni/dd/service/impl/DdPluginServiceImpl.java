package com.yuier.yuni.dd.service.impl;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.detector.MessageDetectorDefiner;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.listener.MessageTypeListener;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import com.yuier.yuni.common.plugin.FunctionPlugins;
import com.yuier.yuni.common.plugin.dto.base.BaseDetectorPluginDto;
import com.yuier.yuni.common.plugin.dto.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.plugin.dto.order.OrderDetectorPluginDto;
import com.yuier.yuni.common.plugin.dto.order.OrderDetectorPluginsDto;
import com.yuier.yuni.common.plugin.dto.positive.PositivePluginDto;
import com.yuier.yuni.common.plugin.dto.positive.PositivePluginsDto;
import com.yuier.yuni.common.plugin.interf.PositivePlugin;
import com.yuier.yuni.common.utils.CallCore;
import com.yuier.yuni.dd.domain.global.DdGlobalData;
import com.yuier.yuni.dd.service.DdPluginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
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
public class DdPluginServiceImpl implements DdPluginService {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DdGlobalData ddGlobalData;
    @Autowired
    CallCore callCore;


    @Override
    public void scanAndBuildPlugin() {
        FunctionPlugins functionPlugins = new FunctionPlugins();
        ddGlobalData.setPlugins(functionPlugins);
        loadPlugins(functionPlugins);
        initialPluginsToCore(functionPlugins);
    }

    @Override
    public void scanAndBuildPlugin(YuniModuleEnum module) {
        FunctionPlugins functionPlugins = new FunctionPlugins();
        ddGlobalData.setPlugins(functionPlugins);
        loadPlugins(functionPlugins);
        initialPluginsToCore(functionPlugins, module);
    }


    private void loadPlugins(FunctionPlugins functionPlugins) {
        // 扫描加了 Plugin 注解的插件
        Map<String, Object> pluginMap = applicationContext.getBeansWithAnnotation(Plugin.class);
        try {
            // 对每个插件
            for (Object pluginBean : pluginMap.values()) {

                // 检查合法性
                Plugin pluginAnnotation = pluginBean.getClass().getAnnotation(Plugin.class);
                String pluginId = pluginAnnotation.id();
                if (functionPlugins.getPluginMap().containsKey(pluginId)) {
                    log.error("插件" + pluginBean + "的 ID " + pluginId + " 与其他插件重复，请检查！");
                    continue;
                }
                // description 方法
                Method descriptionMethod = pluginBean.getClass().getDeclaredMethod(SystemConstants.PLUGIN_CRITICAL_NAME.DESCRIPTION);
                // run 方法
                Method runMethod = null;
                List<Method> methodList = Arrays.stream(pluginBean.getClass().getMethods()).filter(o -> o.getName().equals(SystemConstants.PLUGIN_CRITICAL_NAME.FUNC_PLUGIN_ENTRY)).toList();
                if (methodList.size() > 1) {
                    log.error("插件" + pluginBean + "定义了多个入口方法，请检查！");
                    continue;
                } else if (methodList.isEmpty()) {
                    log.error("插件" + pluginBean + "没有定义入口方法，请检查！");
                    continue;
                }
                runMethod = methodList.get(SystemConstants.FIRST_INDEX);

                // 创建 plugin 实例
                FunctionPlugin funcPlugin = new FunctionPlugin();
                funcPlugin.setPluginId(pluginId);
                funcPlugin.setPluginBean(pluginBean);
                funcPlugin.setDescription((String) descriptionMethod.invoke(pluginBean));
                funcPlugin.setListener(new MessageTypeListener(pluginAnnotation.listener()));
                funcPlugin.setRunMethod(runMethod);
                // 如果插件不是主动触发，那么需要定义一个消息链探测器
                if (!(pluginBean instanceof PositivePlugin)) {
                    // detectorDefine 方法
                    Method detectorDefineMethod = pluginBean.getClass().getDeclaredMethod(SystemConstants.PLUGIN_CRITICAL_NAME.DETECTOR_DEFINE);
                    MessageDetectorDefiner detectorDefiner = (MessageDetectorDefiner) detectorDefineMethod.invoke(pluginBean);
                    if (!detectorDefiner.defineValid()) {
                        log.error("插件 " + pluginBean + " 的消息链探测器定义无效！请检查。");
                        continue;
                    }
                    funcPlugin.setDetectorDefiner(detectorDefiner);
                }
                functionPlugins.getPluginMap().put(pluginId, funcPlugin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("扫描完毕");
    }

    private void initialPluginsToCore(FunctionPlugins functionPlugins) {
        BaseDetectorPluginsDto baseDetectorPluginsDto = new BaseDetectorPluginsDto();
        PositivePluginsDto positivePluginsDto = new PositivePluginsDto();
        OrderDetectorPluginsDto orderDetectorPluginsDto = new OrderDetectorPluginsDto();
        for (FunctionPlugin plugin : functionPlugins.getPluginMap().values()) {
            // 如果是主动消息链探测器
            if (plugin.isPositive()) {
                positivePluginsDto.getPluginDtoMap().put(plugin.getPluginId(), new PositivePluginDto(plugin));
            } else {
                // 如果插件使用了基础消息链探测器
                if (plugin.useDetector(BaseDetectorDefiner.class)) {
                    BaseDetectorPluginDto baseDetectorPluginDto = new BaseDetectorPluginDto(plugin);
                    baseDetectorPluginsDto.getPluginDtoMap().put(baseDetectorPluginDto.getPluginId(), baseDetectorPluginDto);
                } else if (plugin.useDetector(YuniOrderDefiner.class)) {
                    OrderDetectorPluginDto orderDetectorPluginDto = new OrderDetectorPluginDto(plugin);
                    orderDetectorPluginsDto.getPluginDtoMap().put(orderDetectorPluginDto.getPluginId(), orderDetectorPluginDto);
                }
            }
        }
        callCore.initialBaseDetectorPluginToCore(baseDetectorPluginsDto);
        callCore.initialPositivePluginToCore(positivePluginsDto);
        callCore.initialOrderPositivePluginToCore(orderDetectorPluginsDto);
    }

    private void initialPluginsToCore(FunctionPlugins functionPlugins, YuniModuleEnum module) {
        BaseDetectorPluginsDto baseDetectorPluginsDto = new BaseDetectorPluginsDto();
        PositivePluginsDto positivePluginsDto = new PositivePluginsDto(module);
        OrderDetectorPluginsDto orderDetectorPluginsDto = new OrderDetectorPluginsDto();
        for (FunctionPlugin plugin : functionPlugins.getPluginMap().values()) {
            // 如果是主动消息链探测器
            if (plugin.isPositive()) {
                positivePluginsDto.getPositivePluginIdList().add(plugin.getPluginId());
                positivePluginsDto.getPluginDtoMap().put(plugin.getPluginId(), new PositivePluginDto(plugin, module));
            } else {
                // 如果插件使用了基础消息链探测器
                if (plugin.useDetector(BaseDetectorDefiner.class)) {
                    BaseDetectorPluginDto baseDetectorPluginDto = new BaseDetectorPluginDto(plugin, module);
                    baseDetectorPluginsDto.getPluginDtoMap().put(baseDetectorPluginDto.getPluginId(), baseDetectorPluginDto);
                } else if (plugin.useDetector(YuniOrderDefiner.class)) {
                    OrderDetectorPluginDto orderDetectorPluginDto = new OrderDetectorPluginDto(plugin, module);
                    orderDetectorPluginsDto.getPluginDtoMap().put(orderDetectorPluginDto.getPluginId(), orderDetectorPluginDto);
                }
            }
        }
        callCore.initialBaseDetectorPluginToCore(baseDetectorPluginsDto);
        callCore.initialPositivePluginToCore(positivePluginsDto);
        callCore.initialOrderPositivePluginToCore(orderDetectorPluginsDto);
    }

}
