package com.yuier.yuni.core.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (GroupFunctionSetting)表实体类
 *
 * @author liyuier
 * @since 2024-05-30 23:34:41
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cms_group_function_setting")
public class GroupFunctionSettingEntity {
    
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
    * 功能ID
    */
    private String functionId;
    
    /**
    * 开关。1表示开启，0表示关闭
    */
    private Integer functionOn;
    
    /**
    * 删除标志，默认0，表示未删除；1表示删除
    */
    private Integer delFlag;

    public GroupFunctionSettingEntity(Long groupId, String functionId, Integer functionOn) {
        this.groupId = groupId;
        this.functionId = functionId;
        this.functionOn = functionOn;
    }
}
