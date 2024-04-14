package com.yuier.yuni.common.constants;

/**
 * @Title: SystemConstants
 * @Author yuier
 * @Package com.yuier.yuni.common.constants
 * @Date 2024/4/10 23:57
 * @description: 系统常量类
 */
public class SystemConstants {

    public static final String HANDLE_METHODS = "handle";

    public static final class POST_EVENT_MAP_FIELDS {

        public static final String TIME = "time";

        public static final String SELF_ID = "self_id";

        public static final String POST_TYPE = "post_type";

        public static final String EVENT_DATA = "event_data";
    }


    public static final class FIELD_NAMES {

        public static final String POST_TYPE = "postType";

        public static final String MESSAGE_TYPE = "messageType";

        public static final String MESSAGE_SUB_TYPE = "subType";

        public static final String MESSAGE_SENDER_SEX = "sex";

    }

    // OneBot 上报事件类型
    public static final class ONE_BOT_POST_TYPE {


        public static final String MESSAGE = "message";

        public static final String NOTICE = "notice";

        public static final String REQUEST = "request";

        public static final String META = "meta_event";
    }

    // 消息事件类型
    public static final class MESSAGE_TYPE {

        public static final String PRIVATE = "private";

        public static final String GROUP = "group";
    }

    // 消息事件子事件类型
    public static final class MESSAGE_SUB_TYPE {

        // 以下为私聊消息子类型
        public static final String PRIVATE_FRIEND = "friend";

        // 表示私聊消息为群临时会话
        public static final String PRIVATE_GROUP = "group";

        public static final String PRIVATE_OTHER = "other";

        // 以下为群聊消息子类型
        // 表示正常消息
        public static final String GROUP_NORMAL = "normal";

        // 表示匿名消息
        public static final String GROUP_ANONYMOUS = "anonymous";

        // 表示系统提示（如：管理员已禁止群内匿名聊天）
        public static final String GROUP_NOTICE = "notice";
    }

    // 用户相关属性
    public static final class USER {

        public static final String SEX_MALE = "male";

        public static final String SEX_FEMALE = "female";

        public static final String SEX_UNKNOWN = "unknown";

        // 群主
        public static final String ROLE_OWNER = "owner";

        // 管理
        public static final String ROLE_ADMIN = "admin";

        // 群员
        public static final String ROLE_MEMBER = "member";
    }

    // 通知事件类型
    public static final class NOTICE_TYPE {

        // 群文件上传
        public static final String GROUP_UPLOAD = "group_upload";

        // 群管理员变动
        public static final String GROUP_ADMIN = "group_admin";

        // 群成员减少
        public static final String GROUP_DECREASE = "group_decrease";

        // 群成员增加
        public static final String GROUP_INCREASE = "group_increase";

        // 群禁言
        public static final String GROUP_BAN = "group_ban";

        // 好友添加
        public static final String FRIEND_ADD = "friend_add";

        // 群消息撤回
        public static final String GROUP_RECALL = "group_recall";

        // 好友消息撤回
        public static final String FRIEND_RECALL = "friend_recall";

        // 群内戳一戳 / 群红包运气王 / 群成员荣誉变更
        public static final String NOTIFY = "notify";
    }

    // 通知事件子事件类型
    public static final class NOTICE_SUB_TYPE {

        public static final String GROUP_ADMIN_SET = "set";

        public static final String GROUP_ADMIN_UNSET = "unset";

        // 褪裙
        public static final String GROUP_DECREASE_LEAVE = "leave";

        // 成员被踢
        public static final String GROUP_DECREASE_KICK = "kick";

        // 登录号被踢
        public static final String GROUP_DECREASE_KICK_ME = "kick_me";

        // 管理员同意入群
        public static final String GROUP_INCREASE_APPROVE = "approve";

        // 管理员邀请入群
        public static final String GROUP_INCREASE_INCITE = "invite";

        // 实施禁言
        public static final String GROUP_BAN_BAN = "ban";

        // 解除禁言
        public static final String GROUP_BAN_LIFT_BAN = "lift_ban";

        // 群内戳一戳
        public static final String NOTIFY_POKE = "poke";

        // 群红包运气王
        public static final String NOTIFY_LUCKY_KING = "lucky_king";

        // 群成员荣誉变更
        public static final String NOTIFY_HONOR = "honor";
    }

    // 群荣誉
    public static final class GROUP_HONOR {

        // 龙王
        public static final String TALK_ACTIVE = "talkative";

        // 群聊之火
        public static final String PERFORMER = "performer";

        // 快乐源泉
        public static final String EMOTION = "emotion";
    }

    // 请求事件类型
    public static final class REQUEST_TYPE {

        // 加好友请求
        public static final String FRIEND = "friend";

        // 加群请求 / 邀请
        public static final String GROUP = "group";
    }

    // 加群请求子事件
    public static final class REQUEST_SUB_TYPE {

        // 加群请求
        public static final String GROUP_ADD = "add";

        // 邀请进群
        public static final String GROUP_INVITE = "invite";
    }

    // 元事件类型
    public static final class META_EVENT_TYPE {

        // 生命周期
        public static final String LIFE_CYCLE = "lifecycle";

        // 心跳
        public static final String DOKIDOKI = "heartbeat";
    }

    // 元事件子类型
    public static final class META_EVENT_SUB_TYPE {

        /**
         * 目前生命周期元事件中，只有 HTTP POST 的情况下可以收到 enable 和 disable
         * 只有正向 WebSocket 和反向 WebSocket 可以收到 connect。
         */

        // OneBot 启用
        public static final String ENABLE = "enable";

        // OneBot 停用
        public static final String DISABLE = "disable";

        // WebSocket 连接成功
        public static final String CONNECT = "connect";
    }

}
