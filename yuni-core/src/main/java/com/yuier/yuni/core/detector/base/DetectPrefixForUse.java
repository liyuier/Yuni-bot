package com.yuier.yuni.core.detector.base;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.data.TextData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectPrefixForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:44
 * @description: 实际使用的前缀探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectPrefixForUse implements BaseSubDetectorForUse{
    private ArrayList<String> prefixes;

    @Override
    public Boolean hit(MessageChain chain) {
        TextData data = chain.firstTextData();
        if (null != data) {
            for (String pre : prefixes) {
                if (data.getText().startsWith(pre)) {
                    return true;
                }
            }
        }
        return false;
    }
}
