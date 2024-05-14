package com.yuier.yuni.dd.controller;

import com.yuier.yuni.common.domain.dto.CallBaseFunctionPluginDto;
import com.yuier.yuni.common.domain.dto.CallOrderFunctionPluginDto;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.dd.service.DdCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: DdCallController
 * @Author yuier
 * @Package com.yuier.yuni.dd
 * @Date 2024/5/14 22:53
 * @description: 插件功能调用的 Controller 层
 */
@RestController
@RequestMapping("/dd")
public class DdCallController {
    @Autowired
    DdCallService ddCallService;

    @PostMapping("/plugin/base")
    public ResponseResult callBasePlugin(@RequestBody CallBaseFunctionPluginDto callBaseFunctionPluginDto) {
        return ddCallService.callBasePlugin(callBaseFunctionPluginDto);
    }

    @PostMapping("/plugin/order")
    public ResponseResult callOrderPlugin(@RequestBody CallOrderFunctionPluginDto callOrderFunctionPluginDto) {
        return ddCallService.callOrderPlugin(callOrderFunctionPluginDto);
    }
}
