package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderArgsDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionalArgDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderRequiredArgDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: OrderArgsDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 21:54
 * @description: 实际使用的参数集合探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderArgsDetectorForUse {
    private ArrayList<OrderRequiredArgDetectorForUse> requiredArgList;
    private ArrayList<OrderOptionalArgDetectorForUse> optionalArgList;

    public OrderArgsDetectorForUse(YuniOrderArgsDto dto) {
        requiredArgList = new ArrayList<>();
        optionalArgList = new ArrayList<>();

        ArrayList<YuniOrderRequiredArgDto> requiredArgListDto = dto.getRequiredArgList();
        ArrayList<YuniOrderOptionalArgDto> optionalArgListDto = dto.getOptionalArgList();

        for (YuniOrderRequiredArgDto requiredArgDto : requiredArgListDto) {
            requiredArgList.add(new OrderRequiredArgDetectorForUse(requiredArgDto));
        }
        for (YuniOrderOptionalArgDto optionalArgDto : optionalArgListDto) {
            optionalArgList.add(new OrderOptionalArgDetectorForUse(optionalArgDto));
        }
    }
}
