package com.yuier.yuni.core.domain.global.functionhelp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Title: GroupFunctionClose
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.functionhelp
 * @Date 2024/5/30 23:36
 * @description: 群组关闭插件情况
 */
@Data
@AllArgsConstructor
public class GroupFunctionClose {
    /**
     * 键为插件名称
     * 值为将此插件关闭了的群组 ID 集合
     */
    HashMap<String, ArrayList<Long>> closePluginMap;

    public GroupFunctionClose() {
        closePluginMap = new HashMap<>();
    }
}
