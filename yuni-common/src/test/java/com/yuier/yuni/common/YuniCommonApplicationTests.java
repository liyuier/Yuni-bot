package com.yuier.yuni.common;

import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YuniCommonApplicationTests {
    @Autowired
    MessageChainService messageChainService;
    @Autowired
    CallOneBot callOneBot;

    @Test
    void contextLoads() {
    }

    @Test
    void testSendMessage() {
        SendGroupMessageDto dto = new SendGroupMessageDto();
        dto.setGroupId((long)930198267);
        dto.setMessage(messageChainService.buildChain("123").getContent());
        dto.setAutoEscape(false);
        callOneBot.sendGroupMessage(dto);
    }

}
