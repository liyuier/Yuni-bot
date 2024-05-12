package com.yuier.yuni.common.detector.order.dto;

import com.yuier.yuni.common.detector.MessageDetectorDefinerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: YuniOrderDefinerDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/11 23:05
 * @description: 指令定义器 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderDefinerDto implements MessageDetectorDefinerDto {
    private YuniOrderHeadDto head;
    private YuniOrderArgsDto args;
    private YuniOrderOptionsDto options;
}
