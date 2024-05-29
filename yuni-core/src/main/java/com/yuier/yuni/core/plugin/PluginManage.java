package com.yuier.yuni.core.plugin;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.detector.order.YuniOrderDefiner;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.plugin.interf.YuniOrderPlugin;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import com.yuier.yuni.core.domain.global.detector.PluginForDetect;
import com.yuier.yuni.core.domain.global.detector.PluginsForDetect;
import com.yuier.yuni.core.domain.global.detector.base.BasePluginForDetect;
import com.yuier.yuni.core.domain.global.detector.order.OrderPluginForDetect;
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

    private String pluginsView = "pluginsView";

    @Override
    public YuniOrderDefiner detectorDefine() {
        return YuniOrderDefiner.build()
                .setOrderHead("插件")
                .addOption(pluginsView, "-查看");
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent, OrderMatchedOut order) {
        if (order.optionExists(pluginsView)) {
            showPluginsForUse(messageEvent);
        }
        return ResponseResult.okResult();
    }

    private void showPluginsForUse(MessageEvent messageEvent) {
        StringBuilder res = new StringBuilder("插件信息：");
        res.append("\n======== 主动插件：========");
        for (ArrayList<String> pluginIds : coreGlobalData.getPositivePlugins().getPositivePluginMap().values()) {
            for (String pluginId : pluginIds) {
                res.append(String.format("""
                    --------
                    插件 ID：%s
                    """, pluginId));
            }
        }
        res.append("\n======== 被动插件：========");
        for (PluginForDetect plugin : coreGlobalData.getBasePlugins().getPluginMap().values()) {
            res.append(String.format("""
                    --------
                    插件 ID：%s
                    功能描述：%s
                    所属模块：%s
                    """,
                    ((BasePluginForDetect) plugin).getPluginId(),
                    ((BasePluginForDetect) plugin).getDescription(),
                    ((BasePluginForDetect) plugin).getModule()));
        }
        for (PluginForDetect plugin : coreGlobalData.getOrderPlugins().getPluginMap().values()) {
            res.append(String.format("""
                    --------
                    插件 ID：%s
                    功能描述：%s
                    所属模块：%s
                    """,
                    ((OrderPluginForDetect) plugin).getPluginId(),
                    ((OrderPluginForDetect) plugin).getDescription(),
                    ((OrderPluginForDetect) plugin).getModule()));
        }
        callOneBot.sendMessage(new SendMessageDto(
                messageEvent.getMessageEventPosition(),
                messageChainService.buildChain(res.toString())
        ));
    }

    @Override
    public String description() {
        return "插件管理模块";
    }
}
