package com.yuier.yuni.core.runner;

import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.domain.message.res.SendMessageRes;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Title: StartupTestRunner
 * @Author yuier
 * @Package com.yuier.yuni.core.runner
 * @Date 2024/4/16 1:11
 * @description: 启动测试类
 */
@Component
public class StartupTestRunner implements CommandLineRunner {
    @Autowired
    MessageChainService messageChainService;
    @Autowired
    CallOneBot callOneBot;

    @Override
    public void run(String... args) {
        SendGroupMessageDto dto = new SendGroupMessageDto();
        dto.setGroupId((long)930198267);
        dto.setMessage(messageChainService.buildChain("孩子们，我回来了").getContent());
        dto.setAutoEscape(false);
        SendMessageRes res = callOneBot.sendGroupMessage(dto);
    }
}
