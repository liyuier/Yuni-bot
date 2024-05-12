package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderHeadDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: YuniOrderHead
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/9 22:23
 * @description: 指令标识符，即指令的第一个字段
 */
@Data
@AllArgsConstructor
public class YuniOrderHead implements YuniOrderSeg{
    // 指令头
    private String name;

    public YuniOrderHead() {
        name = "";
    }

    public void setHeadName(String headName) {
        if (null == headName || headName.isEmpty()) {
            throw new RuntimeException("指令头名称无效！");
        }
        name = headName;
    }

    @Override
    public Boolean valid() {
        return !name.isEmpty();
    }

    @Override
    public YuniOrderHeadDto toDto() {
        return BeanCopyUtils.copyBean(this, YuniOrderHeadDto.class);
    }
}
