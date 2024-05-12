package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.constants.SystemConstants;
import com.yuier.yuni.common.detector.order.dto.YuniOrderDefinerDto;
import com.yuier.yuni.common.detector.order.matchedout.OrderArgMatchedOut;
import com.yuier.yuni.common.detector.order.matchedout.OrderMatchedOut;
import com.yuier.yuni.common.domain.message.MessageChain;
import com.yuier.yuni.common.domain.message.MessageChainForOrder;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.enums.MessageDataEnum;
import com.yuier.yuni.core.domain.global.CoreGlobalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @Title: OrderDetectorForUse
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/12 21:50
 * @description: 实际使用中的指令探测器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetectorForUse {
    private OrderHeadDetectorForUse head;
    private OrderArgsDetectorForUse args;
    private OrderOptionsDetectorForUse options;

    @Autowired
    CoreGlobalData coreGlobalData;

    public Boolean argsContainsReply() {
        return args.argsContainsReply() || options.argsContainsReply();
    }

    public OrderDetectorForUse(YuniOrderDefinerDto dto) {
        head = new OrderHeadDetectorForUse(dto.getHead());
        args = new OrderArgsDetectorForUse(dto.getArgs());
        options = new OrderOptionsDetectorForUse(dto.getOptions());
    }

    /**
     * 要能匹配上指令，消息要求能拆分成的最少消息段数量
     * 数量为：消息头 1 段加上必要参数数量
     */
    public Integer leastSegNum() {
        return 1 + args.getRequiredArgList().size();
    }

    public Integer leastSegNumForOption(OrderOptionDetectorForUse option) {
        return 1 + option.getOptionArgs().getRequiredArgList().size();
    }

    public Boolean hit(MessageChain chain, OrderMatchedOut orderMatchedOut, String orderMark) {
        /**
         * 预处理一下，由于回复消息固定在消息链的第一条，所以将其放至最后一条，避免干扰判断
         */
        if (chain.startWithReplyData()) {
            chain.getContent().add(chain.getContent().remove(0));
        }
        // 开始判断
        // 如果消息链不是以有效文本开头，直接判不匹配
        if (!chain.startWithTextData()) {
            return false;
        }
        // 拆分消息段，供指令探测器匹配
        MessageChainForOrder chainForOrder = new MessageChainForOrder();
        for (MessageSeg messageSeg : chain.getContent()) {
            if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                String[] strArr = ((TextData) messageSeg.getData()).getText().split(" ");
                for (String str : strArr) {
                    if (!str.trim().isEmpty()) {
                        chainForOrder.addTextSeg(str);
                    }
                }
            } else {
                chainForOrder.getContent().add(messageSeg);
            }
        }
        // 如果拆分出来的消息段数量不能满足指令的最低要求，返回 false
        if (chainForOrder.getContent().size() < leastSegNum()) {
            return false;
        }
        // 匹配指令头
        if (!((TextData) chainForOrder.getContent().get(SystemConstants.FIRST_INDEX).getData()).getText().equals(orderMark + head.getName())) {
            return false;
        }

        /**
         * 匹配参数与选项
         * 采用非贪婪匹配，即如果某段文本消息与选项标识匹配，那么就开始匹配该选项
         */

        // 匹配必选参数
        chainForOrder.setCurSegIndex(1);
        if (!matchRequiredArgs(
                args,
                chainForOrder,
                orderMatchedOut
        )) {
            return false;
        }
        // 如果匹配完了，返回 true
        if (chainForOrder.getCurSegIndex() == chainForOrder.getContent().size()) {
            return true;
        }

        // 匹配可选参数
        matchOptionalArgs(
                args,
                chainForOrder,
                orderMatchedOut
        );
        // 如果匹配完了，返回 true
        if (chainForOrder.getCurSegIndex() == chainForOrder.getContent().size()) {
            return true;
        }

        // 匹配选项
        for (OrderOptionDetectorForUse option : options.getOptionMap().values()) {
            // 如果匹配完了，返回 true
            if (chainForOrder.getCurSegIndex() == chainForOrder.getContent().size()) {
                return true;
            }
            // 如果当前消息段不是文本，返回 false
            if (!chainForOrder.getContent().get(chainForOrder.getCurSegIndex()).typeOf(MessageDataEnum.TEXT)) {
                return false;
            }
            matchOption(option, chainForOrder, orderMatchedOut);
        }
        // 如果匹配完了，返回 true；否则返回 false
        return chainForOrder.getCurSegIndex() == chainForOrder.getContent().size();
    }

    /**
     *
     * @param args  待匹配的参数探测器
     * @param chainForOrder  分割好供匹配的消息链
     * @param orderMatchedOut  要提取出的命令参数
     * @return  能否提取成功
     */
    public Boolean matchRequiredArgs(OrderArgsDetectorForUse args,
                                     MessageChainForOrder chainForOrder,
                                     OrderMatchedOut orderMatchedOut) {
        ArrayList<OrderRequiredArgDetectorForUse> requiredArgList = args.getRequiredArgList();
        for (int i = 0; i < requiredArgList.size(); i ++) {
            // 遍历所有必选参数
            OrderRequiredArgDetectorForUse requiredArg = requiredArgList.get(i);
            MessageSeg messageSeg = chainForOrder.getContent().get(chainForOrder.getCurSegIndex());
            OrderArgMatchedOut orderArgMatchedOut = new OrderArgMatchedOut();
            // 如果必选参数匹配不上当前消息段，返回 false
            if (!OrderArgHitUtil.hit(messageSeg, orderArgMatchedOut,
                    requiredArg.getName(), requiredArg.getContentType())) {
                return false;
            }
            // 将提取出来的参数保存起来
            orderMatchedOut.getArgs().getArgMap().put(requiredArg.getName(), orderArgMatchedOut);
            chainForOrder.setCurSegIndex(chainForOrder.getCurSegIndex() + 1);
        }
        return true;
    }

    public void matchOptionalArgs(OrderArgsDetectorForUse args,
                                     MessageChainForOrder chainForOrder,
                                     OrderMatchedOut orderMatchedOut) {
        ArrayList<OrderOptionalArgDetectorForUse> optionalArgList = args.getOptionalArgList();
        for (int i = 0; i < optionalArgList.size(); i ++) {
            // 遍历所有可选参数
            MessageSeg messageSeg = chainForOrder.getContent().get(chainForOrder.getCurSegIndex());
            /**
             * 非贪婪匹配的实现
             * 如果匹配非必选参数的时候，匹配到某个选项的标识，那么跳出匹配
             */
            if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                String text = ((TextData) messageSeg.getData()).getText();
                if (options.hasFlag(text)) {
                    return;
                }
            }
            OrderOptionalArgDetectorForUse optionalArg = optionalArgList.get(i);
            OrderArgMatchedOut orderArgMatchedOut = new OrderArgMatchedOut();
            // 如果非必选参数匹配不上当前消息段，返回 false
            if (!OrderArgHitUtil.hit(messageSeg, orderArgMatchedOut,
                    optionalArg.getName(), optionalArg.getContentType())) {
                return;
            }
            // 将提取出来的参数保存起来
            orderMatchedOut.getArgs().getArgMap().put(optionalArg.getName(), orderArgMatchedOut);
            chainForOrder.setCurSegIndex(chainForOrder.getCurSegIndex() + 1);
            // 如果匹配完了，返回
            if (chainForOrder.getCurSegIndex() == chainForOrder.getContent().size()) {
                return;
            }
        }
    }

    /**
     * 判断消息链能否匹配上选项
     * @param option  将参与匹配的选项
     * @param chainForOrder  分割好供匹配的消息链
     * @param orderMatchedOut  要提取出的参数
     *
     *
     */
    public void matchOption(OrderOptionDetectorForUse option,
                            MessageChainForOrder chainForOrder,
                            OrderMatchedOut orderMatchedOut) {
        // 保存原始索引，以供匹配不上当前参数时恢复原始状态
        int rawSegIndex = chainForOrder.getCurSegIndex();
        // 消息链过短，匹配不上
        if (chainForOrder.getContent().size() - chainForOrder.getCurSegIndex() < leastSegNumForOption(option)) {
            return;
        }
        // 如果当前消息段与选项标识不匹配，返回
        String text = ((TextData) chainForOrder.getContent().get(chainForOrder.getCurSegIndex()).getData()).getText();
        if (!text.equals(option.getFlag())) {
            return;
        }
        // 匹配当前选项标识，指针右移一位
        chainForOrder.setCurSegIndex(chainForOrder.getCurSegIndex() + 1);
        if (!matchRequiredArgs(
                option.getOptionArgs(),
                chainForOrder,
                orderMatchedOut
        )) {
            chainForOrder.setCurSegIndex(rawSegIndex);
            return;
        }
        // 如果匹配完了，返回
        if (chainForOrder.getCurSegIndex() == chainForOrder.getContent().size()) {
            return;
        }
        // 匹配可选参数
        matchOptionalArgs(
                option.getOptionArgs(),
                chainForOrder,
                orderMatchedOut
        );
    }

}
