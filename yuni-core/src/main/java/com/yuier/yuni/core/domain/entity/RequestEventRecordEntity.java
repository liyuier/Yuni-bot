package com.yuier.yuni.core.domain.entity;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (RequestEventRecord)表实体类
 *
 * @author liyuier
 * @since 2024-04-15 00:07:13
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cms_request_event_record")
public class RequestEventRecordEntity {
    
    /**
    * id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
    * 收到请求的时间戳
    */
    private Date time;
    
    /**
    * 收到消息的机器人 QQ 号
    */
    private Long selfId;
    
    /**
    * 请求类型。加好友请求friend；加群/邀请请求group
    */
    private String requestType;
    
    /**
    * 请求子类型。只有加群/邀请请求有效。加群请求add；邀请请求invite
    */
    private String subType;
    
    /**
    * 发送请求者 QQ 号。只有加好友请求有效。
    */
    private Long userId;
    
    /**
    * 群号。只有加群/邀请请求有效。
    */
    private Long groupId;
    
    /**
    * 验证信息。
    */
    private String comment;
    
    /**
    * 请求 flag，在调用处理请求的 API 时需要传入
    */
    private String flag;
    
    /**
    * 删除标志，默认0，表示未删除；1表示删除
    */
    private Integer delFlag;
}
