package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderArgsDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderSegDto;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgRequireTypeEnum;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: YuniOrderArgs
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order
 * @Date 2024/5/11 0:49
 * @description: 指令参数列表
 */
@Data
@AllArgsConstructor
public class YuniOrderArgs implements YuniOrderSeg{

    private ArrayList<YuniOrderArg> argList;

    public YuniOrderArgs() {
        argList = new ArrayList<>();
    }

    private Boolean argNameExists(String argName) {
        for (YuniOrderArg arg : argList) {
            if (arg.getName().equals(argName)) {
                return true;
            }
        }
        return false;
    }

    private void checkNameValid(String argName) {
        if (null == argName || argName.isEmpty()) {
            throw new RuntimeException("参数名称无效！");
        }
        if (argNameExists(argName)) {
            throw new RuntimeException("本指令中参数名称" + argName + "已存在！");
        }
    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(requireType);
        arg.setName(argName);
        argList.add(arg);
    }

    public void addArg(YuniOrderArg arg) {
        checkNameValid(arg.getName());
        argList.add(arg);
    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType, YuniOrderArgContentTypeEnum contentType) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(requireType);
        arg.setName(argName);
        arg.setContentType(contentType);
        argList.add(arg);

    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType, String helpInfo) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(requireType);
        arg.setName(argName);
        arg.setHelpInfo(helpInfo);
        argList.add(arg);
    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(requireType);
        arg.setName(argName);
        arg.setContentType(contentType);
        arg.setHelpInfo(helpInfo);
        argList.add(arg);
    }

    public void addRequiredArg(String argName) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.REQUIRED);
        arg.setName(argName);
        argList.add(arg);
    }

    public void addRequiredArg(String argName, YuniOrderArgContentTypeEnum contentType) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.REQUIRED);
        arg.setName(argName);
        arg.setContentType(contentType);
        argList.add(arg);
    }

    public void addRequiredArg(String argName, String helpInfo) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.REQUIRED);
        arg.setName(argName);
        arg.setHelpInfo(helpInfo);
        argList.add(arg);
    }

    public void addRequiredArg(String argName, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.REQUIRED);
        arg.setName(argName);
        arg.setContentType(contentType);
        arg.setHelpInfo(helpInfo);
        argList.add(arg);
    }

    public void addOptionalArg(String argName) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.OPTIONAL);
        arg.setName(argName);
        argList.add(arg);
    }

    public void addOptionalArg(String argName, YuniOrderArgContentTypeEnum contentType) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.OPTIONAL);
        arg.setName(argName);
        arg.setContentType(contentType);
        argList.add(arg);
    }

    public void addOptionalArg(String argName, String helpInfo) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.OPTIONAL);
        arg.setName(argName);
        arg.setHelpInfo(helpInfo);
        argList.add(arg);
    }

    public void addOptionalArg(String argName, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        checkNameValid(argName);
        YuniOrderArg arg = new YuniOrderArg();
        arg.setRequireType(YuniOrderArgRequireTypeEnum.OPTIONAL);
        arg.setName(argName);
        arg.setContentType(contentType);
        arg.setHelpInfo(helpInfo);
        argList.add(arg);
    }

    public void addArgs(YuniOrderArgs args) {
        for (YuniOrderArg arg : args.getArgList()) {
            addArg(arg);
        }
    }

    @Override
    public Boolean valid() {
        boolean flag = true;
        for (YuniOrderArg arg : argList) {
            if (!arg.valid()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public YuniOrderArgsDto toDto() {
        return BeanCopyUtils.copyBean(this, YuniOrderArgsDto.class);
    }
}
