package com.yuier.yuni.common.domain.message.data;

import com.yuier.yuni.common.annotation.MessageDataEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: DiceData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.data
 * @Date 2024/4/14 22:01
 * @description: 掷骰子消息段 data 类
 */
@Data
@Component
@NoArgsConstructor
@MessageDataEntity(messageType = "dice")
public class DiceData extends MessageData {
}
