package com.yuier.yuni.core.plugin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.YuniOrderRequiredArg;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;
import com.yuier.yuni.common.plugin.dto.positive.PositivePluginDto;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.entity.GroupFunctionSettingEntity;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.domain.global.detector.PluginForDetect;
import com.yuier.yuni.core.domain.global.detector.base.BasePluginForDetect;
import com.yuier.yuni.core.domain.global.detector.order.OrderPluginForDetect;
import com.yuier.yuni.core.service.CorePluginService;
import com.yuier.yuni.core.service.GroupFunctionSettingService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Title: PluginManage
 * @Author yuier
 * @Package com.yuier.yuni.core.plugin
 * @Date 2024/5/29 23:17
 * @description: 插件管理模块
 */
@Component
@Plugin(id = "PluginManage", listener = MessageTypeEnum.ALL)
public class PluginManage implements YuniOrderPlugin {

    @Autowired
    CoreGlobalData coreGlobalData;
    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;
    @Autowired
    GroupFunctionSettingService groupFunctionSettingService;
    @Autowired
    CorePluginService corePluginService;

    private String pluginsView = "pluginsView";
    private String pluginClose = "pluginClose";
    private String pluginOpen = "pluginOpen";
    private String pluginIdToClose = "pluginIdToClose";
    private String pluginIdToOpen = "pluginIdToOpen";

    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("插件")
                .addOption(pluginsView, "-查看")
                .addOption(pluginClose, "-关闭", new YuniOrderRequiredArg(pluginIdToClose, YuniOrderArgContentTypeEnum.TEXT))
                .addOption(pluginOpen, "-开启", new YuniOrderRequiredArg(pluginIdToOpen, YuniOrderArgContentTypeEnum.TEXT));
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order) {
        if (order.optionExists(pluginsView)) {
            showPluginsForUse(messageEvent);
        }
        if (order.optionExists(pluginClose)) {
            closePlugin(messageEvent, order);
        }
        if (order.optionExists(pluginOpen)) {
            openPlugin(messageEvent, order);
        }
        return ResponseResult.okResult();
    }

    private void showPluginsForUse(MessageEvent messageEvent) {
        StringBuilder res = new StringBuilder("插件信息：");
        res.append("\n======== 主动插件：========\n");
        for (PositivePluginDto plugin : coreGlobalData.getPositivePlugins().getPluginMap().values()) {
            res.append(String.format("""
                    --------
                    插件 ID：%s
                    功能描述：%s
                    所属模块：%s
                    插件状态：%s
                    """,
                    plugin.getPluginId(),
                    plugin.getDescription(),
                    plugin.getModule(),
                    messageEvent.isPrivateMessage() ? "开启" : (
                            coreGlobalData.getGroupFunctionClose().getClosePluginMap()
                                    .getOrDefault(plugin.getPluginId(), new ArrayList<>())
                                    .contains(messageEvent.getGroupId()) ? "关闭" : "开启")
            ));
        }
        res.append("======== 被动插件：========\n");
        for (PluginForDetect plugin : coreGlobalData.getBasePlugins().getPluginMap().values()) {
            res.append(String.format("""
                    --------
                    插件 ID：%s
                    功能描述：%s
                    所属模块：%s
                    插件状态：%s
                    """,
                    ((BasePluginForDetect) plugin).getPluginId(),
                    ((BasePluginForDetect) plugin).getDescription(),
                    ((BasePluginForDetect) plugin).getModule(),
                    messageEvent.isPrivateMessage() ? "开启" : (
                            coreGlobalData.getGroupFunctionClose().getClosePluginMap()
                                    .getOrDefault(plugin.getPluginId(), new ArrayList<>())
                                    .contains(messageEvent.getGroupId()) ? "关闭" : "开启")
            ));
        }
        for (PluginForDetect plugin : coreGlobalData.getOrderPlugins().getPluginMap().values()) {
            res.append(String.format("""
                    --------
                    插件 ID：%s
                    功能描述：%s
                    所属模块：%s
                    插件状态：%s
                    """,
                    ((OrderPluginForDetect) plugin).getPluginId(),
                    ((OrderPluginForDetect) plugin).getDescription(),
                    ((OrderPluginForDetect) plugin).getModule(),
                    messageEvent.isPrivateMessage() ? "开启" : (
                            coreGlobalData.getGroupFunctionClose().getClosePluginMap()
                                    .getOrDefault(plugin.getPluginId(), new ArrayList<>())
                                    .contains(messageEvent.getGroupId()) ? "关闭" : "开启"
                            )
            ));
        }
        callOneBot.sendMessage(new SendMessageDto(
                messageEvent.getMessageEventPosition(),
                messageChainService.buildChain(res.toString())
        ));
    }

    private void closePlugin(MessageEvent messageEvent, OrderMatchedOut order) {
        if (!orderCloseOrOpenOptionValid(messageEvent, order, pluginClose, pluginIdToClose)) {
            return;
        }
        String pluginId = order.getOptionArgByName(pluginClose, pluginIdToClose).asText();
        // 更新 coreGlobalData 中的数据
        ArrayList<Long> closePluginGroupList = coreGlobalData.getGroupFunctionClose().getClosePluginMap()
                .getOrDefault(pluginId, new ArrayList<>());
        closePluginGroupList.add(messageEvent.getGroupId());
        coreGlobalData.getGroupFunctionClose().getClosePluginMap().put(pluginId, closePluginGroupList);
        // 更新数据库
        groupClosePlugin(messageEvent.getGroupId(), pluginId);
        callOneBot.sendMessage(new SendMessageDto(
                messageEvent.getMessageEventPosition(),
                messageChainService.buildChain("插件 " + pluginId + " 已关闭。")
        ));
    }

    private void openPlugin(MessageEvent messageEvent, OrderMatchedOut order) {
        if (!orderCloseOrOpenOptionValid(messageEvent, order, pluginOpen, pluginIdToOpen)) {
            return;
        }
        Long groupId = messageEvent.getGroupId();
        String pluginId = order.getOptionArgByName(pluginOpen, pluginIdToOpen).asText();// 更新 coreGlobalData 中的数据
        // 更新 coreGlobalData 中的数据
        ArrayList<Long> closePluginGroupList = coreGlobalData.getGroupFunctionClose().getClosePluginMap()
                .getOrDefault(pluginId, new ArrayList<>());
        closePluginGroupList.remove(groupId);
        coreGlobalData.getGroupFunctionClose().getClosePluginMap().put(pluginId, closePluginGroupList);
        // 更新数据库
        groupOpenPlugin(messageEvent.getGroupId(), pluginId);
        callOneBot.sendMessage(new SendMessageDto(
                messageEvent.getMessageEventPosition(),
                messageChainService.buildChain("插件 " + pluginId + " 已开启。")
        ));
    }

    private Boolean orderCloseOrOpenOptionValid(MessageEvent messageEvent, OrderMatchedOut order, String option, String pluginIdForOperate) {
        if (messageEvent.isPrivateMessage()) {
            callOneBot.sendMessage(new SendMessageDto(
                    messageEvent.getMessageEventPosition(),
                    messageChainService.buildChain("私聊窗口不支持关闭/开启插件")
            ));
            return false;
        }
        String pluginId = order.getOptionArgByName(option, pluginIdForOperate).asText();
        // 插件不存在，报错
        if (!corePluginService.pluginExists(pluginId)) {
            callOneBot.sendMessage(new SendMessageDto(
                    messageEvent.getMessageEventPosition(),
                    messageChainService.buildChain("插件 " + pluginId + " 不存在！")
            ));
            return false;
        }
        if (corePluginService.positivePluginExists(pluginId)) {
            callOneBot.sendMessage(new SendMessageDto(
                    messageEvent.getMessageEventPosition(),
                    messageChainService.buildChain("插件 " + pluginId + " 为主动插件，目前还不支持关闭/开启")
            ));
            return false;
        }
        if (corePluginService.pluginIsCore(pluginId)) {
            callOneBot.sendMessage(new SendMessageDto(
                    messageEvent.getMessageEventPosition(),
                    messageChainService.buildChain("核心插件不允许关闭/开启")
            ));
            return false;
        }
        return true;
    }

    private void groupClosePlugin(Long groupId, String pluginId) {
        LambdaQueryWrapper<GroupFunctionSettingEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GroupFunctionSettingEntity::getGroupId, groupId)
                .eq(GroupFunctionSettingEntity::getFunctionId, pluginId);
        if (groupFunctionSettingService.exists(queryWrapper)) {
            GroupFunctionSettingEntity one = groupFunctionSettingService.getOne(queryWrapper);
            one.setFunctionOn(SystemConstants.GROUP_PLUGIN_CLOSE);
            groupFunctionSettingService.updateById(one);
        } else {
            groupFunctionSettingService.save(new GroupFunctionSettingEntity(groupId, pluginId, SystemConstants.GROUP_PLUGIN_CLOSE));
        }
    }

    private void groupOpenPlugin(Long groupId, String pluginId) {
        LambdaQueryWrapper<GroupFunctionSettingEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GroupFunctionSettingEntity::getGroupId, groupId)
                .eq(GroupFunctionSettingEntity::getFunctionId, pluginId);
        if (groupFunctionSettingService.exists(queryWrapper)) {
            GroupFunctionSettingEntity one = groupFunctionSettingService.getOne(queryWrapper);
            one.setFunctionOn(SystemConstants.GROUP_PLUGIN_OPEN);
            groupFunctionSettingService.updateById(one);
        }
    }

    @Override
    public String description() {
        return "插件管理模块";
    }
}
