package com.yuier.yuni.common.detector.order.matchedout;

import com.yuier.yuni.common.domain.message.data.AtData;
import com.yuier.yuni.common.domain.message.data.ImageData;
import com.yuier.yuni.common.domain.message.data.ReplyData;
import com.yuier.yuni.common.domain.message.data.TextData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: OrderDataMatchedOut
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.matchedout
 * @Date 2024/5/12 17:59
 * @description: 被提取出的参数的具体数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDataMatchedOut {
    private TextData text;
    private AtData at;
    private ImageData image;
}
