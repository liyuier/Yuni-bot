package com.yuier.yuni.function.service.impl;

import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.domain.dto.CallFunctionPluginDto;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.domain.global.FunctionGlobalData;
import com.yuier.yuni.common.plugin.FunctionPlugin;
import com.yuier.yuni.common.plugin.FunctionPlugins;
import com.yuier.yuni.function.service.FunctionCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Title: FunctionCallServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.function.service.impl
 * @Date 2024/4/16 20:31
 * @description: 功能调用服务接口类
 */
@Service
public class FunctionCallServiceImpl implements FunctionCallService {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    AsyncService asyncService;
    @Autowired
    MessageEventService messageEventService;
    @Autowired
    FunctionGlobalData functionGlobalData;

    @Override
    public ResponseResult callPlugin(CallFunctionPluginDto callFunctionPluginDto) {
        MessageEvent messageEvent = messageEventService.postToMessage(callFunctionPluginDto.getMessageEventNode(), MessageEvent.class);
        FunctionPlugins plugins = functionGlobalData.getPlugins();
        FunctionPlugin plugin = plugins.getPluginMap().get(callFunctionPluginDto.getPluginId());
        try {
            if (plugin.useDetector(BaseDetectorDefiner.class)) {
                asyncService.asyncReflective(plugin.getPluginBean(), SystemConstants.PLUGIN_CRITICAL_NAME.FUNC_PLUGIN_ENTRY, messageEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.okResult();
    }
}
