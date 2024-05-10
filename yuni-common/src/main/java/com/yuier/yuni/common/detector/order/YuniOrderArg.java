package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import com.yuier.yuni.common.enums.MessageDataEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgRequireTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: YuniOrderArgs
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/9 22:25
 * @description: 指令参数字段
 */
@Data
@AllArgsConstructor
public class YuniOrderArg implements YuniOrderSeg{

    // 参数名称
    private String name;

    // 参数为必选参数还是可选参数
    private YuniOrderArgRequireTypeEnum requireType;

    // 参数可以接受的消息数据类型
    private YuniOrderArgContentTypeEnum contentType;

    // 帮助信息
    private String helpInfo;

    public YuniOrderArg() {
        // 默认接收字符串类型的消息
        contentType = YuniOrderArgContentTypeEnum.TEXT;
    }

    @Override
    public Boolean valid() {
        return null != name && !name.isEmpty() && null != requireType;
    }

    @Override
    public YuniOrderSegDto toDto() {
        return null;
    }
}
