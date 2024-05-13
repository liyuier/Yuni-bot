package com.yuier.yuni.common.detector.order.matchedout;

import com.yuier.yuni.common.domain.message.data.AtData;
import com.yuier.yuni.common.domain.message.data.ImageData;
import com.yuier.yuni.common.domain.message.data.ReplyData;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: OrderArgMatchedOut
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.arg
 * @Date 2024/5/12 17:50
 * @description: 被匹配提取出来的参数实体类
 */
@Data
@AllArgsConstructor
public class OrderArgMatchedOut {
    private String name;
    private YuniOrderArgContentTypeEnum contentType;

    private OrderDataMatchedOut data;
    private String helpInfo;

    public OrderArgMatchedOut() {
        name = "";
        contentType = YuniOrderArgContentTypeEnum.TEXT;
        data = new OrderDataMatchedOut();
        helpInfo = "";
    }

    public String asText() {
        if (null == data.getText()) {
            throw new RuntimeException("当前参数无法转为文本类型！");
        }
        return data.getText().getText();
    }

    public Long asLong() {
        if (!contentType.equals(YuniOrderArgContentTypeEnum.NUMBER) || null == data.getText()) {
            throw new RuntimeException("当前参数无法转为 Long 类型！");
        }
        return Long.parseLong(data.getText().getText());
    }

    public Integer asInteger() {
        if (!contentType.equals(YuniOrderArgContentTypeEnum.NUMBER) || null == data.getText()) {
            throw new RuntimeException("当前参数无法转为 Integer 类型！");
        }
        return Integer.parseInt(data.getText().getText());
    }

    public AtData asAt() {
        if (!contentType.equals(YuniOrderArgContentTypeEnum.AT) || null == data.getAt()) {
            throw new RuntimeException("当前参数无法转为 At 类型！");
        }
        return data.getAt();
    }

    public ImageData asImage() {
        if (!contentType.equals(YuniOrderArgContentTypeEnum.IMAGE) || null == data.getImage()) {
            throw new RuntimeException("当前参数无法转为 Image 类型！");
        }
        return data.getImage();
    }

    public String asUrl() {
        if (!contentType.equals(YuniOrderArgContentTypeEnum.URL) || null == data.getText()) {
            throw new RuntimeException("当前参数无法转为 Url 类型！");
        }
        return data.getText().getText();
    }

    public ReplyData asReply() {
        if (!contentType.equals(YuniOrderArgContentTypeEnum.REPLY) || null == data.getReply()) {
            throw new RuntimeException("当前参数无法转为 Url 类型！");
        }
        return data.getReply();
    }

}
