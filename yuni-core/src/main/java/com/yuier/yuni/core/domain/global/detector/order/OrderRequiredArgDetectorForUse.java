package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderRequiredArgDto;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderRequiredArgDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 22:37
 * @description: 实际使用的必选参数探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequiredArgDetectorForUse {
    private String name;
    private YuniOrderArgContentTypeEnum contentType;

    public OrderRequiredArgDetectorForUse(YuniOrderRequiredArgDto dto) {
        name = dto.getName();
        contentType = dto.getContentType();
    }
}
