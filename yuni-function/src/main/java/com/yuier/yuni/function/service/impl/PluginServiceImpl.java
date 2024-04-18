package com.yuier.yuni.function.service.impl;

import com.yuier.yuni.common.annotation.function.OrderCallFunction;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.dto.PluginFunctionDto;
import com.yuier.yuni.function.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
@Service
public class PluginServiceImpl implements PluginService {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public PluginFunctionDto buildPluginFunctionDto() {
        HashMap<String, ArrayList<String>> functionMap = new HashMap<>();
        ArrayList<String> orders = getFunctionOrderNames();
        functionMap.put(SystemConstants.FUNCTION_KIND.ORDER_CALL, orders);

        return new PluginFunctionDto(functionMap);
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
