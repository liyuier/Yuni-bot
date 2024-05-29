package com.yuier.yuni.core.domain.global.detector.base;

import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.detector.base.dto.BaseDetectorDefinerDto;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.enums.BaseDetectorModelEnum;
import com.yuier.yuni.common.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: BaseDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.detector.base
 * @Date 2024/5/6 23:39
 * @description: 实际使用中的基础消息链探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDetectorForUse {
    BaseDetectorModelEnum detectModel;
    ArrayList<BaseSubDetectorForUse> subDetectors;

    public BaseDetectorForUse(BaseDetectorDefinerDto dto) {
        subDetectors = new ArrayList<>();
        detectModel = dto.getDetectModel();
        if (null != dto.getDetectAtBotDto()) {
            subDetectors.add(BeanCopyUtils.copyBean(dto.getDetectAtBotDto(), DetectAtBotForUse.class));
        }
        if (null != dto.getDetectAtUserDto()) {
            subDetectors.add(BeanCopyUtils.copyBean(dto.getDetectAtUserDto(), DetectAtUserForUse.class));
        }
        if (null != dto.getDetectPrefixDto()) {
            subDetectors.add(BeanCopyUtils.copyBean(dto.getDetectPrefixDto(), DetectPrefixForUse.class));
        }
        if (null != dto.getDetectSuffixDto()) {
            subDetectors.add(BeanCopyUtils.copyBean(dto.getDetectSuffixDto(), DetectSuffixForUse.class));
        }
        if (null != dto.getDetectMatchRegexDto()) {
            subDetectors.add(BeanCopyUtils.copyBean(dto.getDetectMatchRegexDto(), DetectMatchRegexForUse.class));
        }
        if (null != dto.getDetectContainKeyWordDto()) {
            subDetectors.add(BeanCopyUtils.copyBean(dto.getDetectContainKeyWordDto(), DetectContainKeyWordForUse.class));
        }
        if (null != dto.getDetectFullMatchTextDto()) {
            subDetectors.add(BeanCopyUtils.copyBean(dto.getDetectFullMatchTextDto(), DetectFullMatchTextForUse.class));
        }
    }

    public BaseDetectorForUse(BaseDetectorDefiner detectorDefiner) {
        this((BaseDetectorDefinerDto) detectorDefiner.toDto());
    }

    public Boolean hit(MessageChain chain) {
        Boolean flag = false;
        // 没有设定匹配模式，说明只有一个探测器
        if (null == detectModel) {
            detectModel = BaseDetectorModelEnum.OR;
        }
        if (detectModel.equals(BaseDetectorModelEnum.OR)) {
            for (BaseSubDetectorForUse subDetector : subDetectors) {
                if (subDetector.hit(chain)) {
                    flag = true;
                    break;
                }
            }
        } else if (detectModel.equals(BaseDetectorModelEnum.AND)) {
            for (BaseSubDetectorForUse subDetector : subDetectors) {
                if (!subDetector.hit(chain)) {
                    break;
                }
                flag = true;
            }
        }
        return flag;
    }
}
