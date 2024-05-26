package com.yuier.yuni.dd.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (SubUper)表实体类
 *
 * @author liyuier
 * @since 2024-05-26 21:50:03
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("dms_sub_uper")
public class SubUperEntity {
    
    /**
    * id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
    * 订阅地点，群组group，或私聊private
    */
    private String subPosition;
    
    /**
    * 订阅地点ID，群组ID或用户ID
    */
    private Long suberId;
    
    /**
    * 订阅的主播UID
    */
    private Long uperId;
    
    /**
    * 订阅的主播昵称
    */
    private String uperName;
    
    /**
    * 用户身份限制，由低到高，限制逐渐加强。默认无限制。只有群组订阅时有效。
    */
    private Integer userLimit;
    
    /**
    * 是否开启动态推送。默认1，开启推送。
    */
    private Integer uperDynamic;
    
    /**
    * 是否开启视频推送。默认1，开启推送。
    */
    private Integer uperVideo;
    
    /**
    * 是否开启直播推送。默认1，开启推送。
    */
    private Integer uperLive;
    
    /**
    * 是否@全体成员。默认0，不艾特。只有群组订阅时有效。
    */
    private Integer atEveryone;
    
    /**
    * 删除标志，默认0，表示未删除；1表示删除
    */
    private Integer delFlag;
}
