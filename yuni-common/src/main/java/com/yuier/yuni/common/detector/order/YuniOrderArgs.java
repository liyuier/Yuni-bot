package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderArgsDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionalArgDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderRequiredArgDto;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgRequireTypeEnum;
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

    private ArrayList<YuniOrderRequiredArg> requiredArgList;
    private ArrayList<YuniOrderOptionalArg> optionalArgList;

    public YuniOrderArgs() {
        requiredArgList = new ArrayList<>();
        optionalArgList = new ArrayList<>();
    }

    private Boolean argNameExists(String argName) {
        for (YuniOrderRequiredArg arg : requiredArgList) {
            if (arg.getName().equals(argName)) {
                return true;
            }
        }
        for (YuniOrderOptionalArg arg : optionalArgList) {
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

    private void checkArgRequireTypeValid() {
        if (!optionalArgList.isEmpty()) {
            throw new RuntimeException("必选参数必须定义在所有可选参数之前！");
        }
    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType) {
        checkNameValid(argName);
        if (requireType.equals(YuniOrderArgRequireTypeEnum.REQUIRED)) {
            YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
            arg.setName(argName);
            requiredArgList.add(arg);
        } else if (requireType.equals(YuniOrderArgRequireTypeEnum.OPTIONAL)) {
            YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
            arg.setName(argName);
            optionalArgList.add(arg);
        }
    }

    public void addArg(YuniOrderRequiredArg arg) {
        checkNameValid(arg.getName());
        requiredArgList.add(arg);
    }

    public void addArg(YuniOrderOptionalArg arg) {
        checkNameValid(arg.getName());
        optionalArgList.add(arg);
    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType, YuniOrderArgContentTypeEnum contentType) {
        checkNameValid(argName);
        if (requireType.equals(YuniOrderArgRequireTypeEnum.REQUIRED)) {
            YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
            arg.setName(argName);
            arg.setContentType(contentType);
            requiredArgList.add(arg);
        } else if (requireType.equals(YuniOrderArgRequireTypeEnum.OPTIONAL)) {
            YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
            arg.setName(argName);
            arg.setContentType(contentType);
            optionalArgList.add(arg);
        }
    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType, String helpInfo) {
        checkNameValid(argName);
        if (requireType.equals(YuniOrderArgRequireTypeEnum.REQUIRED)) {
            YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
            arg.setName(argName);
            arg.setHelpInfo(helpInfo);
            requiredArgList.add(arg);
        } else if (requireType.equals(YuniOrderArgRequireTypeEnum.OPTIONAL)) {
            YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
            arg.setName(argName);
            arg.setHelpInfo(helpInfo);
            optionalArgList.add(arg);
        }
    }

    public void addArg(String argName, YuniOrderArgRequireTypeEnum requireType, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        checkNameValid(argName);
        if (requireType.equals(YuniOrderArgRequireTypeEnum.REQUIRED)) {
            YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
            arg.setName(argName);
            arg.setContentType(contentType);
            arg.setHelpInfo(helpInfo);
            requiredArgList.add(arg);
        } else if (requireType.equals(YuniOrderArgRequireTypeEnum.OPTIONAL)) {
            YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
            arg.setName(argName);
            arg.setContentType(contentType);
            arg.setHelpInfo(helpInfo);
            optionalArgList.add(arg);
        }
    }

    public void addRequiredArg(String argName) {
        checkNameValid(argName);
        YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
        arg.setName(argName);
        requiredArgList.add(arg);
    }

    public void addRequiredArg(String argName, YuniOrderArgContentTypeEnum contentType) {
        checkNameValid(argName);
        YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
        arg.setName(argName);
        arg.setContentType(contentType);
        requiredArgList.add(arg);
    }

    public void addRequiredArg(String argName, String helpInfo) {
        checkNameValid(argName);
        YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
        arg.setName(argName);
        arg.setHelpInfo(helpInfo);
        requiredArgList.add(arg);
    }

    public void addRequiredArg(String argName, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        checkNameValid(argName);
        YuniOrderRequiredArg arg = new YuniOrderRequiredArg();
        arg.setName(argName);
        arg.setContentType(contentType);
        arg.setHelpInfo(helpInfo);
        requiredArgList.add(arg);
    }

    public void addRequiredArg(YuniOrderRequiredArg arg) {
        requiredArgList.add(arg);
    }

    public void addOptionalArg(String argName) {
        checkNameValid(argName);
        YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
        arg.setName(argName);
        optionalArgList.add(arg);
    }

    public void addOptionalArg(String argName, YuniOrderArgContentTypeEnum contentType) {
        checkNameValid(argName);
        YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
        arg.setName(argName);
        arg.setContentType(contentType);
        optionalArgList.add(arg);
    }

    public void addOptionalArg(String argName, String helpInfo) {
        checkNameValid(argName);
        YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
        arg.setName(argName);
        arg.setHelpInfo(helpInfo);
        optionalArgList.add(arg);
    }

    public void addOptionalArg(String argName, YuniOrderArgContentTypeEnum contentType, String helpInfo) {
        checkNameValid(argName);
        YuniOrderOptionalArg arg = new YuniOrderOptionalArg();
        arg.setName(argName);
        arg.setContentType(contentType);
        arg.setHelpInfo(helpInfo);
        optionalArgList.add(arg);
    }

    public void addOptionalArg(YuniOrderOptionalArg arg) {
        optionalArgList.add(arg);
    }

    public void addArgs(YuniOrderArgs args) {
        for (YuniOrderRequiredArg arg : args.getRequiredArgList()) {
            addArg(arg);
        }
        for (YuniOrderOptionalArg arg : args.getOptionalArgList()) {
            addArg(arg);
        }
    }

    public void addRequiredArgArray(YuniOrderRequiredArg[] args) {
        for (YuniOrderRequiredArg arg : args) {
            if (!arg.valid()) {
                throw new RuntimeException("定义参数不合法！");
            }
            requiredArgList.add(arg);
        }
    }

    public void addOptionalArgArray(YuniOrderOptionalArg[] args) {
        for (YuniOrderOptionalArg arg : args) {
            if (!arg.valid()) {
                throw new RuntimeException("定义参数不合法！");
            }
            optionalArgList.add(arg);
        }
    }

    @Override
    public Boolean valid() {
        boolean flag = true;
        for (YuniOrderRequiredArg arg : requiredArgList) {
            if (!arg.valid()) {
                flag = false;
                break;
            }
        }
        for (YuniOrderOptionalArg arg : optionalArgList) {
            if (!arg.valid()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public YuniOrderArgsDto toDto() {
        YuniOrderArgsDto dto = new YuniOrderArgsDto();
        ArrayList<YuniOrderRequiredArgDto> requiredArgListDto = new ArrayList<>();
        ArrayList<YuniOrderOptionalArgDto> optionalArgListDto = new ArrayList<>();
        for (YuniOrderRequiredArg arg : requiredArgList) {
            requiredArgListDto.add(arg.toDto());
        }
        for (YuniOrderOptionalArg arg : optionalArgList) {
            optionalArgListDto.add(arg.toDto());
        }
        dto.setRequiredArgList(requiredArgListDto);
        dto.setOptionalArgList(optionalArgListDto);
        return dto;
    }
}
