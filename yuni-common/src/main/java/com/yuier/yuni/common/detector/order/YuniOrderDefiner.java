package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.MessageDetectorDefiner;
import com.yuier.yuni.common.detector.MessageDetectorDefinerDto;
import com.yuier.yuni.common.enums.MessageDetectorEnum;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Title: YuniOrderDefiner
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/9 22:14
 * @description: 指令定义器
 */
@Data
@Service
public class YuniOrderDefiner implements MessageDetectorDefiner {

    // 指令头
    private YuniOrderHead head;

    // 指令参数，跟在指令头后边，可选参数、可变参数都必须在必选参数后边
    private YuniOrderArgs args;

    // 指令选项，每个选项都是可选的。其结构分为选项标识与选项参数
    private YuniOrderOptions options;

    // 链式调用的操作对象
    private static YuniOrderDefiner definer;

    private YuniOrderDefiner() {

    }

    public static YuniOrderDefiner build() {
        definer = new YuniOrderDefiner();
        definer.setHead(new YuniOrderHead());
        definer.setArgs(new YuniOrderArgs());
        definer.setOptions(new YuniOrderOptions());
        return definer;
    }

    @Override
    public MessageDetectorEnum detectorType() {
        return MessageDetectorEnum.ORDER;
    }

    @Override
    public Boolean defineValid() {
        return null != head && args.valid() && options.valid();
    }

    @Override
    public MessageDetectorDefinerDto toDto() {
        return null;
    }
}
