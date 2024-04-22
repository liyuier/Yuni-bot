package com.yuier.yuni.common.service.impl;

import com.yuier.yuni.common.service.YuniOrderService;
import org.springframework.stereotype.Service;

/**
 * @Title: YuniOrderServiceImpl
 * @Author yuier
 * @Package com.yuier.yuni.common.service.impl
 * @Date 2024/4/21 17:59
 * @description: 命令 service 接口实现类
 */
@Service
public class YuniOrderServiceImpl implements YuniOrderService {

    @Override
    public boolean orderHeadWordLegal(String headRawStr) {
        return false;
    }

    /**
     * 判断命令定义字符串是否基础合法
     * 是否为空，是否包含空格或空白字符
     * @param rawWord
     * @return
     */
    private boolean baseLegal(String rawWord) {
        boolean flag = true;
        if (rawWord.isEmpty()) {
            return false;
        }
        if (rawWord.matches(".*\\s.*")) {
            return false;
        }
        return flag;
    }

    private boolean fullMatchWordLegal(String rawWord) {
        return baseLegal(rawWord);
    }

    /**
     * 判断任选类命令定义字段是否合法
     * [.. |.. ]
     * @param rawWord
     * @return
     */
    private boolean optionalWordLegal(String rawWord) {
        boolean flag = true;
        if (!baseLegal(rawWord)) {
            return false;
        }
        if (!rawWord.endsWith("]")) {
            return false;
        }
        return flag;
    }

    private boolean
}
