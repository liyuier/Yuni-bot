package com.yuier.yuni.common.domain.message.res.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: GetMsgResData
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.message.res.data
 * @Date 2024/4/22 23:08
 * @description: 获取消息相应类 data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetMsgResData {

}
