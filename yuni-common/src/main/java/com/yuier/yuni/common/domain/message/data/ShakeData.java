package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: ShakeData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/14 22:02
 * @description: 窗口抖动消息段（发） data 类
 */
@Data
@Component
@NoArgsConstructor
@MessageDataEntity(messageType = "shake")
public class ShakeData extends MessageData {
}
