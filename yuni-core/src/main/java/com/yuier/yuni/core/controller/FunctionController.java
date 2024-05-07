package com.yuier.yuni.core.controller;

import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.common.plugin.dto.functionplugin.FunctionPluginsDto;
import com.yuier.yuni.common.plugin.dto.functionplugin.base.BaseDetectorPluginsDto;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: FunctionPostController
 * @Author yuier
 * @Package com.yuier.yuni.core.controller
 * @Date 2024/4/15 22:20
 * @description: 接收 function 模块 http 请求的 controller 层
 */
@RestController
@RequestMapping("function")
public class FunctionController {

    @Autowired
    FunctionService functionService;

    @PostMapping("/init_func")
    public ResponseResult initializeFunctions(@RequestBody PluginFunctionDto pluginFunctionDto) {
        functionService.initializeFunctions(pluginFunctionDto);
        return ResponseResult.okResult();
    }

    @PostMapping("/init_func_plugins")
    public ResponseResult initializeFunctionPlugins(@RequestBody FunctionPluginsDto functionPluginsDto) {
        return ResponseResult.okResult();
    }

    @PostMapping("/init_func_base_plugins")
    public ResponseResult initializeFunctionBasePlugins(@RequestBody BaseDetectorPluginsDto baseDetectorPluginsDto) {
        functionService.initialBaseFunctionPlugins(baseDetectorPluginsDto);
        return ResponseResult.okResult();
    }

}
