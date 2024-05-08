package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.function.plugins.interf.PositivePlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Title: YuniFunctionCollect
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/5/9 0:59
 * @description: uni 仙贝功能意见收集
 */
@Slf4j
@Component
@Plugin(id = "Ohayou", listener = MessageTypeEnum.NONE)
public class YuniFunctionCollect implements PositivePlugin {

    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;

    @Override
    @Scheduled(cron = "0 20 * * *")
    public void run() {
        callOneBot.sendGroupMessage(new SendGroupMessageDto(
                287900567L,
                messageChainService.buildChain("偶哈哟，民那桑！")
        ));
    }

    @Override
    public String description() {
        return "uni 仙贝功能意见收集";
    }
}
