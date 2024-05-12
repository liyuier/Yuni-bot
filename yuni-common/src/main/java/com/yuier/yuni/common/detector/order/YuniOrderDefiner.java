package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.MessageDetectorDefiner;
import com.yuier.yuni.common.detector.MessageDetectorDefinerDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderDefinerDto;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgRequireTypeEnum;
import lombok.Data;
import org.springframework.stereotype.Service;

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

    public YuniOrderDefiner setOrderHead(String headName) {
        head.setHeadName(headName.trim());
        return this;
    }

    public YuniOrderDefiner addArg(String argName, YuniOrderArgRequireTypeEnum requireType) {
        args.addArg(argName.trim(), requireType);
        return this;
    }

    public YuniOrderDefiner addArg(String argName, YuniOrderArgRequireTypeEnum requireType, YuniOrderArgContentTypeEnum contentType) {
        args.addArg(argName.trim(), requireType, contentType);
        return this;
    }

    public YuniOrderDefiner addArg(String argName, YuniOrderArgRequireTypeEnum requireType, String helpInfo) {
        args.addArg(argName.trim(), requireType, helpInfo);
        return this;
    }

    public YuniOrderDefiner addArg(String argName, YuniOrderArgRequireTypeEnum requireType, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        args.addArg(argName.trim(), requireType, contentType, helpInfo);
        return this;
    }

    public YuniOrderDefiner addRequiredArg(String argName) {
        args.addRequiredArg(argName.trim());
        return this;
    }

    public YuniOrderDefiner addRequiredArg(String argName, YuniOrderArgContentTypeEnum contentType) {
        args.addRequiredArg(argName.trim(), contentType);
        return this;
    }

    public YuniOrderDefiner addRequiredArg(String argName, String helpInfo) {
        args.addRequiredArg(argName.trim(), helpInfo);
        return this;
    }

    public YuniOrderDefiner addRequiredArg(String argName, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        args.addRequiredArg(argName.trim(), contentType, helpInfo);
        return this;
    }

    public YuniOrderDefiner addOptionalArg(String argName) {
        args.addOptionalArg(argName.trim());
        return this;
    }

    public YuniOrderDefiner addOptionalArg(String argName, YuniOrderArgContentTypeEnum contentType) {
        args.addOptionalArg(argName.trim(), contentType);
        return this;
    }

    public YuniOrderDefiner addOptionalArg(String argName, String helpInfo) {
        args.addOptionalArg(argName.trim(), helpInfo);
        return this;
    }

    public YuniOrderDefiner addOptionalArg(String argName, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        args.addOptionalArg(argName.trim(), contentType, helpInfo);
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag) {
        options.addOption(optName.trim(), optFlag.trim());
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag, YuniOrderRequiredArg arg) {
        options.addOption(optName.trim(), optFlag.trim(), arg);
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag, YuniOrderOptionalArg arg) {
        options.addOption(optName.trim(), optFlag.trim(), arg);
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag, YuniOrderArgs args) {
        options.addOption(optName.trim(), optFlag.trim(), args);
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag, String helpInfo) {
        options.addOption(optName.trim(), optFlag.trim(), helpInfo);
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag, YuniOrderRequiredArg arg, String helpInfo) {
        options.addOption(optName.trim(), optFlag.trim(), arg, helpInfo);
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag, YuniOrderOptionalArg arg, String helpInfo) {
        options.addOption(optName.trim(), optFlag.trim(), arg, helpInfo);
        return this;
    }

    public YuniOrderDefiner addOption(String optName, String optFlag, YuniOrderArgs args, String helpInfo) {
        options.addOption(optName.trim(), optFlag.trim(), args, helpInfo);
        return this;
    }

    @Override
    public Boolean defineValid() {
        return null != head && args.valid() && options.valid();
    }

    @Override
    public MessageDetectorDefinerDto toDto() {
        YuniOrderDefinerDto dto = new YuniOrderDefinerDto();
        dto.setHead(head.toDto());
        dto.setArgs(args.toDto());
        dto.setOptions(options.toDto());
        return dto;
    }
}
