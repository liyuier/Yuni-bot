package com.yuier.yuni.core.runner;

import com.yuier.yuni.common.domain.message.dto.*;
import com.yuier.yuni.common.domain.message.res.*;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
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
public class StartupInitialRunner implements CommandLineRunner {
    @Autowired
    MessageChainService messageChainService;
    @Autowired
    CallOneBot callOneBot;
    @Autowired
    CoreGlobalData coreGlobalData;

    @Override
    public void run(String... args) {
        // 下面是测试用例
        SendGroupMessageDto dto = new SendGroupMessageDto();
        dto.setGroupId((long)930198267);
        dto.setMessage(messageChainService.buildChain("孩子们，我回来了").getContent());
        dto.setAutoEscape(false);
        callOneBot.sendGroupMessage(dto);
    }
}
