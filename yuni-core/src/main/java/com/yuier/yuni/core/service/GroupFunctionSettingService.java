package com.yuier.yuni.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuier.yuni.core.domain.entity.GroupFunctionSettingEntity;

/**
 * (GroupFunctionSetting)表服务接口
 *
 * @author liyuier
 * @since 2024-05-30 23:34:41
 */
public interface GroupFunctionSettingService extends IService<GroupFunctionSettingEntity> {

    void fillGlobalClosePluginCondition();

}

