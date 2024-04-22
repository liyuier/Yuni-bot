package com.yuier.yuni.common.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: YuniOrder
 * @Author yuier
 * @Package com.yuier.yuni.common.order.domain.entity
 * @Date 2024/4/21 17:53
 * @description: 命令实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YuniOrder {

    private YuniOrderHead head;
    private YuniOrderElementChain elementChain;
}
