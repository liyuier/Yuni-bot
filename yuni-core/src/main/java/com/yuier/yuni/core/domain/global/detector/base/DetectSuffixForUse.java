package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.data.TextData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectSuffixForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:46
 * @description: 实际使用的后缀探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectSuffixForUse implements BaseSubDetectorForUse{
    private ArrayList<String> suffixes;

    @Override
    public Boolean hit(MessageChain chain) {
        TextData data = chain.lastTextData();
        if (null != data) {
            for (String suf : suffixes) {
                if (data.getText().startsWith(suf)) {
                    return true;
                }
            }
        }
        return false;
    }
}
