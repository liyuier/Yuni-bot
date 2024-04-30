package com.yuier.yuni.core.runner;

import com.yuier.yuni.common.domain.message.dto.*;
import com.yuier.yuni.common.domain.message.res.*;
import com.yuier.yuni.common.domain.message.res.data.GetGroupMemberInfoResData;
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
//        NoDataRes noDataRes = callOneBot.deleteMsg(new DeleteMsgDto(res.getData().getMessageId()));
//        NoDataRes noDataRes = callOneBot.sendLike(new SendLikeDto(2937818202L));
//        GetFriendListRes friendList = callOneBot.getFriendList();
//        callOneBot.setFriendAddRequest(new SetFriendAddRequestDto("u_N3cMsQzNG31ZGSmth2roUg1713885973", true));
//        GetGroupInfoRes groupInfo = callOneBot.getGroupInfo(new GetGroupInfoDto(930198267L));
//        GetGroupListRes groupList = callOneBot.getGroupList();
        GetGroupMemberInfoResData groupMemberInfo = callOneBot.getGroupMemberInfo(new GetGroupMemberInfoDto(930198267L, 2937818202L));
        GetGroupMemberListRes groupMemberListRes = callOneBot.getGroupMemberListRes(new GetGroupMemberListDto(930198267L));
    }
}
