package com.yuier.yuni.function.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.annotation.function.OrderCallFunction;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.service.AsyncService;
import com.yuier.yuni.common.service.MessageEventService;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.service.FunctionCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public ResponseResult orderCallFunction(JsonNode messageEventNode) {
        MessageEvent messageEvent = messageEventService.postToMessage(messageEventNode, MessageEvent.class);
        String order = ((TextData) messageEvent.getMessage().getContent().get(SystemConstants.FIRST_INDEX).getData()).getText();
        Map<String, Object> orderCallFunctionBeans = applicationContext.getBeansWithAnnotation(OrderCallFunction.class);
        for (Object bean : orderCallFunctionBeans.values()) {
            OrderCallFunction annotation = bean.getClass().getAnnotation(OrderCallFunction.class);
            if (("/" + annotation.orderWord()).equals(order)) {
                try {
                    asyncService.asyncReflective(bean, messageEvent, SystemConstants.PLUGIN_ENTRY_NAME.FUNC_PLUGIN_ENTRY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseResult.okResult();
    }
}
