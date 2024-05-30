package com.yuier.yuni.core.domain.global;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.enums.OrderMarkEnum;
import com.yuier.yuni.common.plugin.FunctionPlugins;
import com.yuier.yuni.core.domain.global.detector.PluginsForDetect;
import com.yuier.yuni.core.domain.global.functionhelp.GroupFunctionClose;
import com.yuier.yuni.core.domain.global.positiveplugins.PositivePlugins;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Title: GlobalData
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global
 * @Date 2024/4/16 19:28
 * @description: Core 全局数据类
 */
@Data
@Component
@AllArgsConstructor
public class CoreGlobalData {

    // 指令标识符
    private OrderMarkEnum orderMark;

    // 每次接收到 OneBot 上送事件后将其储存下来
    private JsonNode postEventNode;

    // 使用了基础消息探测器的插件
    private PluginsForDetect basePlugins;

    // 使用了指令消息探测器的插件
    private PluginsForDetect orderPlugins;

    // 主动触发的插件
    private PositivePlugins positivePlugins;

    // 核心模块的原始插件 Bean
    private FunctionPlugins rawCorePlugins;

    // 群组关闭插件的情况
    private GroupFunctionClose groupFunctionClose;

    @Value("${bot.self}")
    private Long botId;

    private String nickname = "uni";

    public CoreGlobalData() {
        this.positivePlugins = new PositivePlugins();
        this.basePlugins = new PluginsForDetect();
        this.orderPlugins = new PluginsForDetect();
        this.orderMark = OrderMarkEnum.SLASH;
        this.groupFunctionClose = new GroupFunctionClose();
    }
}
