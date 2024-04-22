package com.yuier.yuni.common.domain.order.definer;

import com.yuier.yuni.common.exception.OrderBuildException;
import com.yuier.yuni.common.service.YuniOrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Title: OrderDefiner
 * @Author yuier
 * @Package com.yuier.yuni.common.domain.order.definer
 * @Date 2024/4/21 23:03
 * @description: 命令定义工具类
 */
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDefiner {
    /**
     * 静态方法 build 返回自身实例，启动链式调用
     * 链式调用的方法实际上并非操作实例，而是操作实例自身下的 definer 字段
     * 所有数据都储存在该字段中
     */

    @Autowired
    YuniOrderService yuniOrderService;

    boolean orderDefineLegal = true;

    // 链式调用操作对象
    private static OrderDefiner definer;

    private String headRawStr;
    private ArrayList<String> elementRawList;

    public static OrderDefiner build() {
        definer = new OrderDefiner();
        definer.setHeadRawStr("");
        definer.setElementRawList(new ArrayList<>());
        return definer;
    }

    public OrderDefiner setHead(String str) {
        str = str.trim();
        if (yuniOrderService.orderHeadWordLegal(str)) {
            definer.setHeadRawStr(str);
        } else {
            orderDefineLegal = false;
            throw new OrderBuildException(str);
        }
        return this;
    }

    public OrderDefiner addElement(String str) {
        str = str.trim();
        definer.getElementRawList().add(str);
        return this;
    }
}
