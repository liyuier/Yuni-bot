package com.yuier.yuni.function.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.service.FunctionCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Title: FunctionCallController
 * @Author yuier
 * @Package com.yuier.yuni.function.controller
 * @Date 2024/4/16 20:28
 * @description: 插件功能调用的 Controller 层
 */
@RestController
@RequestMapping("/function")
public class FunctionCallController {
    @Autowired
    FunctionCallService functionCallService;

    @PostMapping("/order")
    public ResponseResult orderCallFunction(@RequestBody JsonNode messageEvent) {
        return functionCallService.orderCallFunction(messageEvent);
    }
}