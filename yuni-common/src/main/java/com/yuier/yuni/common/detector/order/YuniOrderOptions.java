package com.yuier.yuni.common.detector.order;

import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionDto;
import com.yuier.yuni.common.detector.order.dto.YuniOrderOptionsDto;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Title: YuniOrderOptions
 * @Author yuier
 * @Package com.yuier.yuni.common.detector.order.dto
 * @Date 2024/5/11 1:04
 * @description: 指令选项的集合
 */
@Data
@AllArgsConstructor
public class YuniOrderOptions implements YuniOrderSeg{

    private ArrayList<YuniOrderOption> optionList;

    public YuniOrderOptions() {
        optionList = new ArrayList<>();
    }

    public void checkNameAndFlagValid(String optName, String optFlag) {
        if (null == optName || optName.isEmpty() ||
                null == optFlag || optFlag.isEmpty()) {
            throw new RuntimeException("选项名称与标识均不能为空！");
        }
        for (YuniOrderOption option : optionList) {
            if (option.getName().equals(optName) ||
                    option.getFlag().equals(optFlag)) {
                throw new RuntimeException("选项名称" + optName + "或标识" + optFlag + "已存在！");
            }
        }
    }

    public void addOption(String optName, String optFlag) {
        checkNameAndFlagValid(optName, optFlag);
        YuniOrderOption option = new YuniOrderOption();
        option.setName(optName);
        option.setFlag(optFlag);
        optionList.add(option);
    }

    public void addOption(String optName, String optFlag, YuniOrderArg arg) {
        checkNameAndFlagValid(optName, optFlag);
        YuniOrderOption option = new YuniOrderOption();
        option.setName(optName);
        option.setFlag(optFlag);
        YuniOrderArgs args = new YuniOrderArgs();
        args.addArg(arg);
        option.setOptionArgs(args);
        optionList.add(option);
    }

    public void addOption(String optName, String optFlag, YuniOrderArgs args) {
        checkNameAndFlagValid(optName, optFlag);
        YuniOrderOption option = new YuniOrderOption();
        option.setName(optName);
        option.setFlag(optFlag);
        option.setOptionArgs(args);
        optionList.add(option);
    }

    public void addOption(String optName, String optFlag, String helpInfo) {
        checkNameAndFlagValid(optName, optFlag);
        YuniOrderOption option = new YuniOrderOption();
        option.setName(optName);
        option.setFlag(optFlag);
        option.setHelpInfo(helpInfo);
        optionList.add(option);
    }

    public void addOption(String optName, String optFlag, YuniOrderArg arg, String helpInfo) {
        checkNameAndFlagValid(optName, optFlag);
        YuniOrderOption option = new YuniOrderOption();
        option.setName(optName);
        option.setFlag(optFlag);
        option.getOptionArgs().addArg(arg);
        option.setHelpInfo(helpInfo);
        optionList.add(option);
    }

    public void addOption(String optName, String optFlag, YuniOrderArgs args, String helpInfo) {
        checkNameAndFlagValid(optName, optFlag);
        YuniOrderOption option = new YuniOrderOption();
        option.setName(optName);
        option.setFlag(optFlag);
        option.getOptionArgs().addArgs(args);
        option.setHelpInfo(helpInfo);
        optionList.add(option);
    }

    @Override
    public Boolean valid() {
        boolean flag = true;
        for (YuniOrderOption option : optionList) {
            if(!option.valid()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public YuniOrderOptionsDto toDto() {
        YuniOrderOptionsDto dto = new YuniOrderOptionsDto();
        ArrayList<YuniOrderOptionDto> optionListDto = new ArrayList<>();
        for (YuniOrderOption option : optionList) {
            optionListDto.add(option.toDto());
        }
        dto.setOptionList(optionListDto);
        return dto;
    }
}
