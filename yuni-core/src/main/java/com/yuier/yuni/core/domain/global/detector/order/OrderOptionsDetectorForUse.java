package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Title: OrderOptionsDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 21:54
 * @description: 实际使用的选项集合探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOptionsDetectorForUse {
    private HashMap<String, OrderOptionDetectorForUse> optionMap;

    public Boolean argsContainsReply() {
        for (OrderOptionDetectorForUse optionDetector : optionMap.values()) {
            if (optionDetector.getOptionArgs().argsContainsReply()) {
                return true;
            }
        }
        return false;
    }

    public OrderOptionsDetectorForUse(YuniOrderOptionsDto dto) {
        optionMap = new HashMap<>();
        ArrayList<YuniOrderOptionDto> optionListDto = dto.getOptionList();
        for (YuniOrderOptionDto optionDto : optionListDto) {
            optionMap.put(optionDto.getFlag(), new OrderOptionDetectorForUse(optionDto));
        }
    }

    public Boolean hasFlag(String str) {
        for (OrderOptionDetectorForUse orderOption : optionMap.values()) {
            if (orderOption.getFlag().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
