package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.domain.message.dto.SendMessageDto;
import com.yuier.yuni.common.domain.message.dto.SendPrivateMessageDto;
import com.yuier.yuni.common.domain.message.res.data.GetLoginInfoResData;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: EventLogUtils
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/4/20 23:09
 * @description: 上报事件日志组装工具类
 */
@Slf4j
@Service
public class EventLogUtils {

    @Lazy
    @Autowired
    CallOneBot callOneBot;
    
    @Value("${bot.self}")
    private Long botSelf;

    @Value("${bot.self}")
    private Long botSelfId;

    @Value("${bot.self-name}")
    private String botSelfName;


    private String escapeString(String input) {
        StringBuilder escaped = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '\r':
                    escaped.append("\\r");
                    break;
                case '\n':
                    escaped.append("\\n");
                    break;
                case '\t':
                    escaped.append("\\t");
                    break;
                case '\\':
                    escaped.append("\\\\");
                    break;
                default:
                    escaped.append(c);
            }
        }
        return escaped.toString();
    }

    public String buildMsgSender(MessageEvent messageEvent) {
        String groupCard = messageEvent.getSender().getCard();
        return (null != groupCard && !groupCard.isEmpty() ?
                    groupCard : messageEvent.getSender().getNickname()) +
                "<" + messageEvent.getUserId() + ">";
    }
    private String buildRedLog(String input) {
        return "\033[31m" + input + "\033[0m";
    }

    private String buildGreenLog(String input) {
        return "\033[32m" + input + "\033[0m";
    }

    private String buildYellowLog(String input) {
        return "\033[33m" + input + "\033[0m";
    }

    // 靛青
    private String buildCyanLog(String input) {
        return "\033[36m" + input + "\033[0m";
    }

    // 亮红
    private String buildBrightRedLog(String input) {
        return "\033[91m" + input + "\033[0m";
    }

    private String buildBrightBlueLog(String input) {
        return "\033[92m" + input + "\033[0m";
    }

    public String getReceiveMessageLog(MessageEvent messageEvent) {
        StringBuilder sb = new StringBuilder();
        if (messageEvent.isGroupMessage()) {
            if (messageEvent.isAnonymousMessage()) {
                sb.append((new SimpleDateFormat("yyyy-MM-dd HH:mm"))
                                .format(new Date(messageEvent.getTime() * 1000L))).append(" ")
                        .append("匿名用户").append(buildBrightRedLog(messageEvent.getSender().getNickname())).append(" ")
                        .append("于 ").append(buildCyanLog(messageEvent.getGroupId().toString())).append(" ")
                        .append("发送消息 ").append(buildBrightBlueLog(messageEvent.getMessage().toString()));
            } else {
                sb.append((new SimpleDateFormat("yyyy-MM-dd HH:mm"))
                                .format(new Date(messageEvent.getTime() * 1000L))).append(" ")
                        .append(buildBrightRedLog(buildMsgSender(messageEvent))).append(" ")
                        .append("于 ").append(buildCyanLog(messageEvent.getGroupId().toString())).append(" ")
                        .append("发送消息 ").append(buildBrightBlueLog(messageEvent.getMessage().toString()));
            }
        } else if (messageEvent.isPrivateMessage()) {
            sb.append((new SimpleDateFormat("yyyy-MM-dd HH:mm"))
                            .format(new Date(messageEvent.getTime() * 1000L))).append(" ")
                    .append(buildBrightRedLog(buildMsgSender(messageEvent))).append(" ")
                    .append("向机器人发送私聊消息 ").append(buildBrightBlueLog(messageEvent.getMessage().toString()));
        }
        return escapeString(sb.toString());
    }

    public String getSendMessageLog(SendMessageDto dto) {
        StringBuilder sb = new StringBuilder();
        if (dto.getMessageType().equals(MessageTypeEnum.PRIVATE.toString())) {
            sb.append((new SimpleDateFormat("yyyy-MM-dd HH:mm"))
                    .format(new Date(System.currentTimeMillis()))).append(" ")
                    .append("bot ").append(buildBrightRedLog(botSelfName + "<" + botSelfId + "> "))
                    .append("向用户 ").append(buildCyanLog(dto.getUserId().toString())).append(" ")
                    .append("发送消息 ").append(buildBrightBlueLog(dto.toString()));
        } else if (dto.getMessageType().equals(MessageTypeEnum.GROUP.toString())) {
            sb.append((new SimpleDateFormat("yyyy-MM-dd HH:mm"))
                            .format(new Date(System.currentTimeMillis() * 1000L))).append(" ")
                    .append("bot ").append(buildBrightRedLog(botSelfName + "<" + botSelfId + "> "))
                    .append("向群聊 ").append(buildCyanLog(dto.getGroupId().toString())).append(" ")
                    .append("发送消息 ").append(buildBrightBlueLog(dto.toString()));
        }
        return escapeString(sb.toString());
    }
    
    public String getSendPrivateMessageLog(SendPrivateMessageDto dto) {
        StringBuilder sb = new StringBuilder();
        sb.append((new SimpleDateFormat("yyyy-MM-dd HH:mm"))
                        .format(new Date(System.currentTimeMillis()))).append(" ")
                .append("bot ").append(buildBrightRedLog(botSelfName + "<" + botSelfId + "> "))
                .append("向用户 ").append(buildCyanLog(dto.getUserId().toString())).append(" ")
                .append("发送消息 ").append(buildBrightBlueLog(dto.toString()));
        return escapeString(sb.toString());
    }

    public String getSendGroupMessageLog(SendGroupMessageDto dto) {
        StringBuilder sb = new StringBuilder();
        sb.append((new SimpleDateFormat("yyyy-MM-dd HH:mm"))
                        .format(new Date(System.currentTimeMillis()))).append(" ")
                .append("bot ").append(buildBrightRedLog(botSelfName + "<" + botSelfId + "> "))
                .append("向群聊 ").append(buildCyanLog(dto.getGroupId().toString())).append(" ")
                .append("发送消息 ").append(buildBrightBlueLog(dto.toString()));
        return escapeString(sb.toString());
    }

    public void printRcvMsgEventLog(MessageEvent messageEvent) {
        log.info(getReceiveMessageLog(messageEvent));
    }

    public void printSendMsgLog(SendMessageDto dto) {
        log.info(getSendMessageLog(dto));
    }

    public void printSendMsgLog(MessageEvent messageEvent) {
        if (messageEvent.isGroupMessage()) {
            log.info(getSendGroupMessageLog(new SendGroupMessageDto(
                    messageEvent.getGroupId(),
                    messageEvent.getMessage()
            )));
        } else if (messageEvent.isPrivateMessage()) {
            log.info(getSendPrivateMessageLog(new SendPrivateMessageDto(
                    messageEvent.getUserId(),
                    messageEvent.getMessage()
            )));
        }
    }
    
    public void printSendPrivateMsgLog(SendPrivateMessageDto dto) {
        log.info(getSendPrivateMessageLog(dto));
    }
    
    public void printSendGroupMsgLog(SendGroupMessageDto dto) {
        log.info(getSendGroupMessageLog(dto));
    }
}
