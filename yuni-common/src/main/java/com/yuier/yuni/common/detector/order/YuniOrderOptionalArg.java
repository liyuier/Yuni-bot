package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionalArgDto;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Title: YuniOrderOptionalArg
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/12 22:07
 * @description: 可选参数
 */
@Data
@AllArgsConstructor
public class YuniOrderOptionalArg implements YuniOrderSeg{

    // 参数名称
    private String name;

    // 参数可以接受的消息数据类型
    private YuniOrderArgContentTypeEnum contentType;

    // 帮助信息
    private String helpInfo;

    public YuniOrderOptionalArg() {
        contentType = YuniOrderArgContentTypeEnum.TEXT;
    }

    public YuniOrderOptionalArg(String name) {
        this.name = name;
    }

    public YuniOrderOptionalArg(String name, YuniOrderArgContentTypeEnum contentType) {
        this.name = name;
        this.contentType = contentType;
    }

    public YuniOrderOptionalArg(String name, String helpInfo) {
        this.name = name;
        this.helpInfo = helpInfo;
    }

    @Override
    public Boolean valid() {
        return null != name && !name.isEmpty();
    }

    @Override
    public YuniOrderOptionalArgDto toDto() {
        return BeanCopyUtils.copyBean(this, YuniOrderOptionalArgDto.class);
    }
}
