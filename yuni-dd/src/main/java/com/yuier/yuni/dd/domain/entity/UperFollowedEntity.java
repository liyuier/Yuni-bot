package com.yuier.yuni.dd.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (UperFollowed)表实体类
 *
 * @author liyuier
 * @since 2024-05-28 23:46:10
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("dms_uper_followed")
public class UperFollowedEntity {
    
    /**
    * id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
    * UP 主 id
    */
    private Long uperId;
    
    /**
    * UP 主昵称
    */
    private String uperName;
    
    /**
    * UP 主头像
    */
    private String uperFace;
}
