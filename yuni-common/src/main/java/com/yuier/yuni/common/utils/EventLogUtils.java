package com.yuier.yuni.common.utils;

import com.yuier.yuni.common.domain.message.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

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
public class EventLogUtils {

    private static String escapeString(String input) {
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

    private static String buildMsgSender(MessageEvent messageEvent) {
        String groupCard = messageEvent.getSender().getCard();
        return (null != groupCard && ! groupCard.isEmpty() ?
                    messageEvent.getSender().getCard() : messageEvent.getSender().getNickname()) +
                "<" + messageEvent.getUserId() + ">";
    }
    private static String buildRedLog(String input) {
        return "\033[31m" + input + "\033[0m";
    }

    private static String buildGreenLog(String input) {
        return "\033[32m" + input + "\033[0m";
    }

    private static String buildYellowLog(String input) {
        return "\033[33m" + input + "\033[0m";
    }

    // 靛青
    private static String buildCyanLog(String input) {
        return "\033[36m" + input + "\033[0m";
    }

    // 亮红
    private static String buildBrightRedLog(String input) {
        return "\033[91m" + input + "\033[0m";
    }

    private static String buildBrightBlueLog(String input) {
        return "\033[92m" + input + "\033[0m";
    }


    public static void printMsgEventLog(MessageEvent messageEvent) {
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
        log.info(escapeString(sb.toString()));
    }
}
