package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.plugin.interf.PositivePlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Title: Ohayou
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/5/8 23:11
 * @description: 偶哈哟，民那桑
 */
@Slf4j
@Component
@Plugin(id = "Ohayou", listener = MessageTypeEnum.NONE)
public class Ohayou implements PositivePlugin {

    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;

    @Override
    public String description() {
        return "哦哈哟，米娜桑！";
    }

    @Override
    @Scheduled(cron = "0 30 8 * * ?")
    public void run() {
        Long[] groups = new Long[] {930198267L, 287900567L, 389976995L};
        for (Long groupId : groups) {
            callOneBot.sendGroupMessage(new SendGroupMessageDto(
                    groupId,
                    messageChainService.buildChain("哦哈哟，米娜桑！")
            ));
        }
    }
}
