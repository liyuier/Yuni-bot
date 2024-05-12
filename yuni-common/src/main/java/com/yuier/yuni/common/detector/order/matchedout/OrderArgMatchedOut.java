package com.yuier.yuni.common.detector.order.matchedout;

import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderArgMatchedOut
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.arg
 * @Date 2024/5/12 17:50
 * @description: 被匹配提取出来的参数实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderArgMatchedOut {
    private String name;
    private YuniOrderArgContentTypeEnum contentType;

    private OrderDataMatchedOut data;
    private String helpInfo;
}
