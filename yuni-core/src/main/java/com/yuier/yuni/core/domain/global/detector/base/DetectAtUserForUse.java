package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: DetectAtUserForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:48
 * @description: 实际使用的 @ 用户探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectAtUserForUse implements BaseSubDetectorForUse{
    public Boolean atUser;
    /**
     * 可以设置一个或多个具体 @ 目标
     * 当设置多个目标时，需要设置匹配模式为 “或” 还是 “与”
     */
    public ArrayList<Long> targets;
    public BaseDetectorModelEnum model;

    @Override
    public Boolean hit(MessageChain chain) {
        if (null == targets || targets.isEmpty()) {
            return chain.atUser();
        } else {
            if (targets.size() == 1) {
                model = BaseDetectorModelEnum.OR;
            }
            Boolean flag = false;
            if (model.equals(BaseDetectorModelEnum.OR)) {
                for (Long target : targets) {
                    if (chain.atUser(target.toString())) {
                        flag = true;
                        break;
                    }
                }
            } else if (model.equals(BaseDetectorModelEnum.AND)) {
                for (Long target : targets) {
                    if (!chain.atUser(target.toString())) {
                        break;
                    }
                }
                flag = true;
            }
            return flag;
        }
    }
}
