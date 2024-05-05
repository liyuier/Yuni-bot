package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.annotation.function.OrderCallFunction;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.plugins.interf.YuniOrderPlugin;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @Title: GenShinStart
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins.impl
 * @Date 2024/4/16 23:45
 * @description: 原神，启动！
 */
@Component
@Plugin(id = "GenShinStart", listener = MessageTypeEnum.ALL)
@OrderCallFunction(orderWord = "原神")
public class GenShinStart implements YuniOrderPlugin {

    @Autowired
    Environment environment;
    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;

    @Value("${bot.owner}")
    private Long ownerQQ;

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent) {
        if (Objects.equals(ownerQQ, messageEvent.getUserId())) {
            callOneBot.sendGroupMessage(new SendGroupMessageDto(
                    messageEvent.getGroupId(),
                    messageChainService.buildChain("启动！")
            ));
        } else {
            callOneBot.sendGroupMessage(new SendGroupMessageDto(
                    messageEvent.getGroupId(),
                    messageChainService.buildChain("对不起，您没有权限启动原神。")
            ));
        }
        return ResponseResult.okResult();
    }

    @Override
    public String description() {
        return "原神，启动！";
    }
}
