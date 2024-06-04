package com.yuier.yuni.core.service.impl;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.detector.MessageDetectorDefiner;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.enums.YuniModuleEnum;
import com.yuier.yuni.common.listener.MessageTypeListener;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import com.yuier.yuni.common.plugin.FunctionPlugins;
import com.yuier.yuni.common.plugin.dto.positive.PositivePluginDto;
import com.yuier.yuni.common.plugin.interf.PositivePlugin;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.domain.global.detector.base.BasePluginForDetect;
import com.yuier.yuni.core.domain.global.detector.order.OrderPluginForDetect;
import com.yuier.yuni.core.service.CorePluginService;
import com.yuier.yuni.core.service.FunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Title: CorePluginServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.core.service.impl
 * @Date 2024/5/29 22:22
 * @description: 核心模块插件服务实现类
 */
@Slf4j
@Service
public class CorePluginServiceImpl implements CorePluginService {

    @Autowired
    CoreGlobalData coreGlobalData;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    FunctionService functionService;

    @Override
    public void scanAndBuildPlugin(YuniModuleEnum module) {
        loadPluginsForUse();
        buildPluginForUse(module);
    }

    private void buildPluginForUse(YuniModuleEnum module) {
        String coreModuleName = module.getName();
        for (FunctionPlugin pluginBean : coreGlobalData.getRawCorePlugins().getPluginMap().values()) {
            if (pluginBean.isPositive()) {  // 主动插件
                coreGlobalData.getPositivePlugins().getPluginMap().put(pluginBean.getPluginId(), new PositivePluginDto(pluginBean, module));
            } else {
                if (pluginBean.useDetector(BaseDetectorDefiner.class)) {  // 使用基础消息链探测器
                    coreGlobalData.getBasePlugins().getPluginMap().put(
                            pluginBean.getPluginId(),
                            new BasePluginForDetect(pluginBean, module)
                    );
                } else if (pluginBean.useDetector(YuniOrderDefiner.class)) {
                    coreGlobalData.getOrderPlugins().getPluginMap().put(
                            pluginBean.getPluginId(),
                            new OrderPluginForDetect(pluginBean, module)
                    );
                }
            }
        }
    }

    private void loadPluginsForUse() {
        FunctionPlugins functionPlugins = new FunctionPlugins();
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
        coreGlobalData.setRawCorePlugins(functionPlugins);
        log.info("插件扫描完毕");
    }

    @Override
    public Boolean pluginExists(String pluginId) {
        if (positivePluginExists(pluginId)) {
            return true;
        }
        if (coreGlobalData.getBasePlugins().getPluginMap().containsKey(pluginId)) {
            return true;
        }
        if (coreGlobalData.getOrderPlugins().getPluginMap().containsKey(pluginId)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean positivePluginExists(String pluginId) {
        return coreGlobalData.getPositivePlugins().getPluginMap().containsKey(pluginId);
    }

    @Override
    public Boolean pluginIsCore(String pluginId) {
        if (coreGlobalData.getBasePlugins().getPluginMap().containsKey(pluginId)) {
            return ((BasePluginForDetect) coreGlobalData.getBasePlugins().getPluginMap().get(pluginId)).getModule().equals(YuniModuleEnum.CORE);
        }
        if (coreGlobalData.getOrderPlugins().getPluginMap().containsKey(pluginId)) {
            OrderPluginForDetect orderPluginForDetect = (OrderPluginForDetect) coreGlobalData.getOrderPlugins().getPluginMap().get(pluginId);
            return orderPluginForDetect.getModule().equals(YuniModuleEnum.CORE);
        }
        return false;
    }


}
