package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.detector.base.dto.DetectAtBotDto;
import com.yuier.yuni.common.domain.message.MessageChain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: DetectAtBotForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:47
 * @description: 实际使用的 @ 机器人探测器
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class DetectAtBotForUse implements BaseSubDetectorForUse{
    public Boolean atBot;

    public DetectAtBotForUse(DetectAtBotDto dto) {
        atBot = dto.getAtBot();
    }

//    @Value("${bot.self}")
//    private String botSelf;

    @Autowired
    private MessageChain messageChain;


    @Override
    public Boolean hit(MessageChain chain) {
//        return chain.atBot();
//        return chain.atUser(botSelf);
        return chain.atUser(messageChain.getSelfId());

    }
}
