package com.yuier.yuni.core.domain.entity;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (FunctionCallRecord)表实体类
 *
 * @author liyuier
 * @since 2024-04-09 23:39:58
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cms_function_call_record")
public class FunctionCallRecordEntity {
    
    /**
    * id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
    * 功能调用时间
    */
    private Date time;
    
    /**
    * 群组ID
    */
    private Long groupId;
    
    /**
    * 调用功能的用户ID
    */
    private Long userId;
    
    /**
    * 功能ID
    */
    private String functionId;
    
    /**
    * 调用结果
    */
    private String result;
    
    /**
    * 删除标志，默认0，表示未删除；1表示删除
    */
    private Integer delFlag;
}
