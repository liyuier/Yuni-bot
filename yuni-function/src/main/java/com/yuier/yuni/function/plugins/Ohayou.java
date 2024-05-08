package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.plugins.interf.PositivePlugin;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

/**
 * @Title: Ohayou
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/5/8 23:11
 * @description: 偶哈哟，民那桑
 */
@Slf4j
@Component
@Plugin(id = "Ohayou", listener = MessageTypeEnum.NONE)
public class Ohayou implements PositivePlugin {
    @Override
    public String description() {
        return "偶哈哟，民那桑";
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent) {
        return ResponseResult.okResult();
    }
}
