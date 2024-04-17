package com.yuier.yuni.function.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.utils.ResponseResult;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Title: FunctionCallService
 * @Author yuier
 * @Package com.yuier.yuni.function.service
 * @Date 2024/4/16 20:29
 * @description: 功能调用服务接口
 */
public interface FunctionCallService {

    ResponseResult orderCallFunction(Map<String, Object> messageEvent);

    ResponseResult orderCallFunction(JsonNode messageEvent);
}
