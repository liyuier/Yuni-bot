package com.yuier.yuni.core.domain.event.notice;

import com.yuier.yuni.common.annotation.OneBotNoticeEventEntity;
import com.yuier.yuni.common.constants.SystemConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Title: GroupUploadNotice
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.notice
 * @Date 2024/4/10 23:50
 * @description: 群文件上传类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@OneBotNoticeEventEntity(noticeType = SystemConstants.NOTICE_TYPE.GROUP_UPLOAD)
public class GroupUploadNoticeEvent {

    // 收到消息的时间戳
    private Date time;

    // 收到消息的机器人QQ号
    private Long selfId;

    // 事件类型
    private String postType;

    // 通知类型，值为 group_upload
    private String noticeType;
}
