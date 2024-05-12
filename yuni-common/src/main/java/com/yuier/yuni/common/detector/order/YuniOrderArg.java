package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderArgDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import com.yuier.yuni.common.enums.MessageDataEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgRequireTypeEnum;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.temporal.ChronoUnit;

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
        // 默认必选
        requireType = YuniOrderArgRequireTypeEnum.REQUIRED;
        // 默认接收字符串类型的消息
        contentType = YuniOrderArgContentTypeEnum.TEXT;
    }

    public YuniOrderArg(String name) {
        this();
        this.name = name;
    }

    public YuniOrderArg(String name, YuniOrderArgRequireTypeEnum requireType) {
        this();
        this.name = name;
        this.requireType = requireType;
    }

    public YuniOrderArg(String name, YuniOrderArgContentTypeEnum contentType) {
        this();
        this.name = name;
        this.contentType = contentType;
    }

    public YuniOrderArg(String name, YuniOrderArgRequireTypeEnum requireType, YuniOrderArgContentTypeEnum contentType) {
        this.name = name;
        this.requireType = requireType;
        this.contentType = contentType;
    }

    @Override
    public Boolean valid() {
        return null != name && !name.isEmpty() && null != requireType;
    }

    @Override
    public YuniOrderArgDto toDto() {
        return BeanCopyUtils.copyBean(this, YuniOrderArgDto.class);
    }
}
