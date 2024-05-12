package com.yuier.yuni.core.domain.global;

import com.fasterxml.jackson.databind.JsonNode;
import com.yuier.yuni.common.enums.OrderMarkEnum;
import com.yuier.yuni.core.domain.global.detector.PluginsForDetect;
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

    private OrderMarkEnum orderMark;

    private JsonNode postEventNode;

    private PluginsForDetect pluginsForDetect;

    private PluginsForDetect basePlugins;

    private PluginsForDetect orderPlugins;

    private PositivePlugins positivePlugins;

    @Value("${bot.self}")
    private Long botId;

    private String nickname = "uni";

    public CoreGlobalData() {
        this.pluginsForDetect = new PluginsForDetect();
        this.positivePlugins = new PositivePlugins();
        this.basePlugins = new PluginsForDetect();
        this.orderPlugins = new PluginsForDetect();
        this.orderMark = OrderMarkEnum.SLASH;
    }
}
