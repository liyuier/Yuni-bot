package com.yuier.yuni.common.detector.order.dto;

import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: YuniOrderRequiredArgDto
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/12 22:07
 * @description: 必选参数 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrderRequiredArgDto implements YuniOrderSegDto{
    // 参数名称
    private String name;

    // 参数可以接受的消息数据类型
    private YuniOrderArgContentTypeEnum contentType;
}
