package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.detector.base.dto.DetectAtBotDto;
import com.yuier.yuni.common.domain.message.MessageChain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Title: DetectAtBotForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:47
 * @description: 实际使用的 @ 机器人探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectAtBotForUse implements BaseSubDetectorForUse{
    public Boolean atBot;

    public DetectAtBotForUse(DetectAtBotDto dto) {
        atBot = dto.getAtBot();
    }

    @Value("${bot.self}")
    private Long botSelf;

    @Override
    public Boolean hit(MessageChain chain) {
        return chain.atUser(botSelf);
    }
}
