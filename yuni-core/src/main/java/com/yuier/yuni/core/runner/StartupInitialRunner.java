package com.yuier.yuni.core.runner;

import com.yuier.yuni.common.domain.message.dto.*;
import com.yuier.yuni.common.domain.message.res.data.GetLoginInfoResData;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.service.GroupFunctionSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Title: StartupTestRunner
 * @Author yuier
 * @Package com.yuier.yuni.core.runner
 * @Date 2024/4/16 1:11
 * @description: CoreGlobalData 数据填充
 */
@Component
public class StartupInitialRunner implements CommandLineRunner {
    @Autowired
    MessageChainService messageChainService;
    @Autowired
    CallOneBot callOneBot;
    @Autowired
    CoreGlobalData coreGlobalData;
    @Autowired
    GroupFunctionSettingService groupFunctionSettingService;

    @Override
    public void run(String... args) {
        GetLoginInfoResData data = callOneBot.getLoginInfo().getData();
        coreGlobalData.setBotId(data.getUserId());
        coreGlobalData.setNickname(data.getNickname());

        // 填充群组关闭插件信息
        groupFunctionSettingService.fillGlobalClosePluginCondition();

        // 启动词
        SendGroupMessageDto dto = new SendGroupMessageDto();
        dto.setGroupId((long)930198267);
        dto.setMessage(messageChainService.buildChain("孩子们，我回来了").getContent());
        dto.setAutoEscape(false);
        callOneBot.sendGroupMessage(dto);
    }
}
