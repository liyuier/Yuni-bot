package com.yuier.yuni.common.detector.base;

import com.yuier.yuni.common.detector.base.dto.DetectAtBotDto;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: AtBot
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.base
 * @Date 2024/5/4 3:58
 * @description: @ 机器人探测器
 */
@Data
@AllArgsConstructor
public class DetectAtBot implements BaseSubDetector {

    public Boolean atBot;

    public DetectAtBot() {
        atBot = false;
    }

    public void setTrue() {
        atBot = true;
    }

    @Override
    public Boolean valid() {
        return atBot;
    }

    @Override
    public DetectAtBotDto toDto() {
        return BeanCopyUtils.copyBean(this, DetectAtBotDto.class);
    }
}
