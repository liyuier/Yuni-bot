package com.yuier.yuni.common.constants;

/**
 * @Title: SystemConstants
 * @Author yuier
 * @Package com.yuier.yuni.common.constants
 * @Date 2024/4/10 23:57
 * @description: 系统常量类
 */
public class SystemConstants {

    public static final Integer FIRST_INDEX = 0;

    // OneBot 上报数据关键字段
    public static final class POST_KEY_FIELD {

        public static final String POST_TYPE = "post_type";
        public static final String MESSAGE = "message";
        public static final String MESSAGE_TYPE = "type";
        public static final String MESSAGE_DATA = "data";
    }

    // 插件类 Bean 入口方法
    public static final class PLUGIN_ENTRY_NAME {
        // OneBot 上报事件处理器 handler 入口方法
        public static final String HANDLER_ENTRY = "handle";

        // 功能插件入口方法
        public static final String FUNC_PLUGIN_ENTRY = "run";

        // 消息链探测器入口方法
        public static final String MSG_DETECTOR_ENTRY = "detect";
    }

}
