package com.yuier.yuni.common.bilibiliapi.dto.user.relation;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yuier.yuni.common.bilibiliapi.dto.AbstractData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Title: UpSelfRelation
 * @Author yuier
 * @Package com.yuier.yuni.common.bilibiliapi.dto.user.relation
 * @Date 2024/5/28 23:52
 * @description: 用户与自己的关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpSelfRelation extends AbstractData {
    // 目标用户 mid
    private Long mid;
    /**
     * 关系属性
     * 0：未关注
     * 2：已关注
     * 6：已互粉
     * 128：已拉黑
     */
    private Integer attribute;
    /**
     * 关注对方时间
     * 时间戳
     * 未关注为 0
     */
    private Long mtime;
    /**
     * 分组 id
     * null默认分组
     * array存在至少一个分组
     * 数组元素：
     * n 位于分组（n+1）的分组
     */
    private ArrayList<Integer> tag;
    /**
     * 特别关注标志
     * 0：否
     * 1：是
     */
    private Integer special;
}
