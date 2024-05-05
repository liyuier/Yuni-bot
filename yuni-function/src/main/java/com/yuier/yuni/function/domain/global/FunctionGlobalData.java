package com.yuier.yuni.function.domain.global;

import com.yuier.yuni.function.domain.plugin.FunctionPlugins;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Title: FunctionGlobal
 * @Author yuier
 * @Package com.yuier.yuni.function.domain.global
 * @Date 2024/5/6 0:21
 * @description: function 模块的全局数据类
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class FunctionGlobalData {
    FunctionPlugins plugins;
}
