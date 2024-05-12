package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderRequiredArgDto;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: YuniOrderRequiredArg
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/12 22:04
 * @description: 必选参数
 */
@Data
@AllArgsConstructor
public class YuniOrderRequiredArg implements YuniOrderSeg{
    // 参数名称
    private String name;

    // 参数可以接受的消息数据类型
    private YuniOrderArgContentTypeEnum contentType;

    // 帮助信息
    private String helpInfo;

    public YuniOrderRequiredArg() {
        contentType = YuniOrderArgContentTypeEnum.TEXT;
    }

    public YuniOrderRequiredArg(String name) {
        this();
        this.name = name;
    }

    public YuniOrderRequiredArg(String name, YuniOrderArgContentTypeEnum contentType) {
        this();
        this.name = name;
        this.contentType = contentType;
    }

    public YuniOrderRequiredArg(String name, String helpInfo) {
        this();
        this.name = name;
        this.helpInfo = helpInfo;
    }

    @Override
    public Boolean valid() {
        return null != name && !name.isEmpty();
    }

    @Override
    public YuniOrderRequiredArgDto toDto() {
        return BeanCopyUtils.copyBean(this, YuniOrderRequiredArgDto.class);
    }
}
