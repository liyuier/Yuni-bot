package com.yuier.yuni.common.utils;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.domain.message.dto.*;
import com.yuier.yuni.common.domain.message.res.*;
import com.yuier.yuni.common.domain.message.res.data.GetGroupMemberInfoResData;
import com.yuier.yuni.common.domain.message.res.data.GetGroupMemberListResData;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.service.YuniHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @Title: CallOneBotUtils
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/4/15 22:14
 * @description: 调用 OneBot 实现 API 的工具类
 */
@Component
public class CallOneBot {
    @Autowired
    YuniHttpService yuniHttpService;
    @Autowired
    MessageChainService messageChainService;

    @Value("${base-urls.one-bot}")
    private String baseUrl;

    private String getBaseUrl() {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return baseUrl;
    }

    private <T> T mapJsonNodeToObj(JsonNode jsonNode, T targetObj) throws IllegalAccessException {
        Field[] fields = targetObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            JsonNode value = jsonNode.get(StrUtil.toUnderlineCase(fieldName));
            if (value != null) {
                if (fieldName.equals(SystemConstants.POST_KEY_FIELD.MESSAGE)) {
                    field.set(targetObj, messageChainService.buildChain((ArrayNode) value));
                } else if (value instanceof ObjectNode) {
                    Class<?> targetType = field.getType();
                    field.set(targetObj, mapJsonNodeToObj(value, targetType));
                } else { // TODO 重写
                    if (field.getType().equals(String.class)) {
                        // 如果字段是文本，则直接设置值
                        field.set(targetObj, value.asText());
                    } else if (field.getType().equals(Boolean.class)) {
                        field.set(targetObj, value.asBoolean());
                    } else if (field.getType().equals(Integer.class)) {
                        field.set(targetObj, value.asInt());
                    } else if (field.getType().equals(Long.class)) {
                        field.set(targetObj, value.asLong());
                    } else if (field.getType().equals(Float.class) || field.getType().equals(Double.class)) {
                        field.set(targetObj, value.asDouble());
                    }
                }
            }
        }
        return targetObj;
    }

    private <T> T checkResForDataObj(JsonNode resNode, Class<T> dataClazz) {
        T targetObj = null;
        try {
            targetObj = dataClazz.getDeclaredConstructor().newInstance();
            String retStatus = resNode.get(SystemConstants.ONEBOT_RES_FIELD.STATUE).asText();
            if (!retStatus.equals(SystemConstants.ONEBOT_OK_STATUS)) {
                throw new RuntimeException("OneBot 接口响应不正常：" + retStatus);
            }
            JsonNode resData = resNode.get(SystemConstants.ONEBOT_RES_FIELD.DATA);
            mapJsonNodeToObj(resData, targetObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetObj;
    }

//    private <T> ArrayList<T> checkResForDataList(JsonNode resNode, Class<T> dataClazz) {
//        return
//    }

    public SendMessageRes sendMessage(SendMessageDto dto) {
        String url = getBaseUrl() + "send_msg";
        return yuniHttpService.postRequestForObject(url, dto, SendMessageRes.class);
    }

    public SendMessageRes sendPrivateMessage(SendPrivateMessageDto dto) {
        String url = getBaseUrl() + "send_msg";
        return yuniHttpService.postRequestForObject(url, dto, SendMessageRes.class);
    }

    public SendMessageRes sendGroupMessage(SendGroupMessageDto dto) {
        String url = getBaseUrl() + "send_msg";
        return yuniHttpService.postRequestForObject(url, dto, SendMessageRes.class);
    }

    public GetLoginInfoRes getLoginInfo() {
        String url = getBaseUrl() + "get_login_info";
        return yuniHttpService.postRequestForObject(url, GetLoginInfoRes.class);
    }

    public NoDataRes deleteMsg(DeleteMsgDto dto) {
        String url = getBaseUrl() + "delete_msg";
        return yuniHttpService.postRequestForObject(url, dto, NoDataRes.class);
    }

    public NoDataRes sendLike(SendLikeDto dto) {
        String url = getBaseUrl() + "send_like";
        return yuniHttpService.postRequestForObject(url, dto, NoDataRes.class);
    }

    public GetFriendListRes getFriendList() {
        String url = getBaseUrl() + "get_friend_list";
        return yuniHttpService.postRequestForObject(url, GetFriendListRes.class);
    }

    public NoDataRes setFriendAddRequest(SetFriendAddRequestDto dto) {
        String url = getBaseUrl() + "set_friend_add_request";
        return yuniHttpService.postRequestForObject(url, dto, NoDataRes.class);
    }

    public GetGroupInfoRes getGroupInfo(GetGroupInfoDto dto) {
        String url = getBaseUrl() + "get_group_info";
        return yuniHttpService.postRequestForObject(url, dto, GetGroupInfoRes.class);
    }

    public GetGroupListRes getGroupList() {
        String url = getBaseUrl() + "get_group_list";
        return yuniHttpService.postRequestForObject(url, GetGroupListRes.class);
    }

    public GetGroupMemberInfoResData getGroupMemberInfo(GetGroupMemberInfoDto dto) {
        String url = getBaseUrl() + "get_group_member_info";
        return checkResForDataObj(yuniHttpService.postRequestForJsonNode(url, dto), GetGroupMemberInfoResData.class);
    }

    public GetGroupMemberListRes getGroupMemberListRes(GetGroupMemberListDto dto) {
        String url = getBaseUrl() + "get_group_member_list ";
        return yuniHttpService.postRequestForObject(url, dto, GetGroupMemberListRes.class);
    }
}
