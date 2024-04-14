package com.yuier.yuni.function.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (KeyReply)表实体类
 *
 * @author liyuier
 * @since 2024-04-15 00:08:41
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("fms_key_reply")
public class KeyReplyEntity {
    
    /**
    * id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
    * 群组ID
    */
    private Long groupId;
    
    /**
    * 关键字
    */
    private String key;
    
    /**
    * 回复，消息段格式
    */
    private String reply;
    
    /**
    * 删除标志，默认0，表示未删除；1表示删除
    */
    private Integer delFlag;
}
