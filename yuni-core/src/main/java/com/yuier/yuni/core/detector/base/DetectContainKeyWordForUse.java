package com.yuier.yuni.core.detector.base;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectContainKeyWordForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:48
 * @description: 实际使用的包含关键词探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectContainKeyWordForUse implements BaseSubDetectorForUse{
    private ArrayList<String> keyWords;
    private BaseDetectorModelEnum model;

    @Override
    public Boolean hit(MessageChain chain) {
        if (keyWords.size() == 1) {
            model = BaseDetectorModelEnum.OR;
        }
        Boolean flag = false;
        if (model.equals(BaseDetectorModelEnum.AND)) {
            for (String str : keyWords) {
                if (!chain.containText(str)) {
                    break;
                }
            }
            flag = true;
        } else if (model.equals(BaseDetectorModelEnum.OR)) {
            for (String str : keyWords) {
                if (chain.containText(str)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}
