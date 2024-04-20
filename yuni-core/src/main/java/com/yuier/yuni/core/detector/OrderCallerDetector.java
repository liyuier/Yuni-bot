package com.yuier.yuni.core.detector;

import com.yuier.yuni.common.annotation.FunctionCallerDetector;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.enums.FunctionCallerEnum;
import com.yuier.yuni.common.utils.CallFunction;
import com.yuier.yuni.core.domain.global.GlobalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Title: OrderCallerDetector
 * @Author yuier
 * @Package com.yuier.yuni.core.detector
 * @Date 2024/4/16 23:01
 * @description: 检测消息链是否命中命令
 */
@Component
@FunctionCallerDetector(callerKind = FunctionCallerEnum.ORDER)
public class OrderCallerDetector implements YuniMessageDetector{
    @Autowired
    GlobalData globalData;
    @Autowired
    CallFunction callFunction;

    @Override
    public boolean detect(MessageEvent messageEvent) {
        MessageChain chain = messageEvent.getMessage();
        ArrayList<MessageSeg> content = chain.getContent();
        String firstSegType = content.get(0).getType();
        if (!firstSegType.equals("text")) {
            return false;
        }
        TextData firstText = (TextData) chain.getContent().get(0).getData();
        if (!firstText.getText().startsWith("/")) {
            return false;
        }
        ArrayList<String> orderCallers = globalData.getFunctions().get(FunctionCallerEnum.ORDER.toString());
        boolean flag = false;
        for (String orderCaller : orderCallers) {
            if (firstText.getText().startsWith("/" + orderCaller)) {
                flag = true;
                callFunction.orderCallFunction(globalData.getPostEventNode());
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean detect(MessageChain chain) {
        if (null == chain.getContent() ||chain.getContent().isEmpty()) {
            return false;
        }
        ArrayList<MessageSeg> content = chain.getContent();
        String firstSegType = content.get(0).getType();
        if (!firstSegType.equals("text")) {
            return false;
        }
        TextData firstText = (TextData) chain.getContent().get(0).getData();
        if (!firstText.getText().startsWith("/")) {
            return false;
        }
        ArrayList<String> orderCallers = globalData.getFunctions().get(FunctionCallerEnum.ORDER.toString());
        boolean flag = false;
        for (String orderCaller : orderCallers) {
            if (firstText.getText().startsWith("/" + orderCaller)) {
                flag = true;
                callFunction.orderCallFunction(globalData.getPostEventNode());
                break;
            }
        }
        return flag;
    }
}
