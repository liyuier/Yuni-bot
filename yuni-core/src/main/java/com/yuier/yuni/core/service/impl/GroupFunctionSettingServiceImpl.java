package com.yuier.yuni.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.mapper.GroupFunctionSettingMapper;
import com.yuier.yuni.core.service.GroupFunctionSettingService;
import com.yuier.yuni.core.domain.entity.GroupFunctionSettingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * (GroupFunctionSetting)表服务实现类
 *
 * @author liyuier
 * @since 2024-05-30 23:34:41
 */
@Service
public class GroupFunctionSettingServiceImpl extends ServiceImpl<GroupFunctionSettingMapper, GroupFunctionSettingEntity> implements GroupFunctionSettingService {

    @Autowired
    CoreGlobalData coreGlobalData;

    @Override
    public void fillGlobalClosePluginCondition() {
        List<GroupFunctionSettingEntity> groupFunctionSettingList = list();
        HashMap<String, ArrayList<Long>> coreClosePluginMap = coreGlobalData.getGroupFunctionClose().getClosePluginMap();
        for (GroupFunctionSettingEntity groupFunctionSetting : groupFunctionSettingList) {
            if (Objects.equals(groupFunctionSetting.getFunctionOn(), SystemConstants.GROUP_PLUGIN_CLOSE)) {
                ArrayList<Long> closePluginGroupList = coreClosePluginMap.getOrDefault(
                        groupFunctionSetting.getFunctionId(),
                        new ArrayList<>()
                );
                closePluginGroupList.add(groupFunctionSetting.getGroupId());
                coreClosePluginMap.put(
                        groupFunctionSetting.getFunctionId(),
                        closePluginGroupList
                );
            }
        }
    }

}

