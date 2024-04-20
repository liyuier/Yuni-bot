package com.yuier.yuni.common.enums;

/**
 * @Title: MsgDataEnum
 * @Author yuier
 * @Package com.yuier.yuni.common.enums
 * @Date 2024/4/20 18:17
 * @description: 消息段类型枚举类
 */
public enum MsgDataEnum {

    ANONYMOUS("anonymous"),
    AT("at"),
    CONTACT("contact"),
    DICE("dice"),
    FACE("face"),
    FILE("file"),
    FORWARD("forward"),
    IMAGE("image"),
    JSON("json"),
    LOCATION("location"),
    MARKDOWN("markdown"),
    MARKETFACE("mface"),
    MUSIC("music"),
    NODE("node"),
    POKE("poke"),
    RECORD("record"),
    REPLY("reply"),
    RPS("rps"),
    SHAKE("shake"),
    SHARE("share"),
    TEXT("text"),
    VIDEO("video"),
    XML("xml");

    private final String typeName;

    private MsgDataEnum(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}
