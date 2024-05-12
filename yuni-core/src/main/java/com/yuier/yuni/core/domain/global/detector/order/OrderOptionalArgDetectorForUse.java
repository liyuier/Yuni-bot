package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionalArgDto;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderOptionalArgDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 22:38
 * @description: 实际使用的可选参数探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOptionalArgDetectorForUse {
    private String name;
    private YuniOrderArgContentTypeEnum contentType;

    public OrderOptionalArgDetectorForUse(YuniOrderOptionalArgDto dto) {
        name = dto.getName();
        contentType = dto.getContentType();
    }
}
