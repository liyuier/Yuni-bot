package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

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
    private ArrayList<OrderOptionDetectorForUse> optionList;

    public OrderOptionsDetectorForUse(YuniOrderOptionsDto dto) {
        optionList = new ArrayList<>();
        ArrayList<YuniOrderOptionDto> optionListDto = dto.getOptionList();
        for (YuniOrderOptionDto optionDto : optionListDto) {
            optionList.add(new OrderOptionDetectorForUse(optionDto));
        }
    }
}
