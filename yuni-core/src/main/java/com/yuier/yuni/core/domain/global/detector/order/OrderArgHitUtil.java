package com.yuier.yuni.core.domain.global.detector.order;

import com.yuier.yuni.common.detector.order.matchedout.OrderArgMatchedOut;
import com.yuier.yuni.common.domain.message.MessageSeg;
import com.yuier.yuni.common.domain.message.data.AtData;
import com.yuier.yuni.common.domain.message.data.ImageData;
import com.yuier.yuni.common.domain.message.data.ReplyData;
import com.yuier.yuni.common.domain.message.data.TextData;
import com.yuier.yuni.common.enums.MessageDataEnum;
import com.yuier.yuni.common.enums.YuniOrderArgContentTypeEnum;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * @Title: OrderArgHitUtil
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.global.detector.order
 * @Date 2024/5/13 1:43
 * @description: 判断消息段是否命中参数工具类
 */
public class OrderArgHitUtil {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static Boolean hit(MessageSeg messageSeg, OrderArgMatchedOut orderArgMatchedOut,
                                    String name, YuniOrderArgContentTypeEnum contentType) {
        if (contentType.equals(YuniOrderArgContentTypeEnum.TEXT)) {
            // 匹配类型为文本
            if (!messageSeg.typeOf(MessageDataEnum.TEXT)) {
                return false;
            }
            setOrderArgMatchedOut(
                    orderArgMatchedOut,
                    name,
                    contentType,
                    (TextData) messageSeg.getData()
            );
            return true;
        } else if (contentType.equals(YuniOrderArgContentTypeEnum.NUMBER)) {
            // 匹配类型为数字
            if (!messageSeg.typeOf(MessageDataEnum.TEXT)) {
                return false;
            }
            String text = ((TextData) messageSeg.getData()).getText();
            if (!NUMBER_PATTERN.matcher(text).matches()) {
                return false;
            }
            setOrderArgMatchedOut(
                    orderArgMatchedOut,
                    name,
                    contentType,
                    (TextData) messageSeg.getData()
            );
            return true;
        } else if (contentType.equals(YuniOrderArgContentTypeEnum.AT)) {
            // 匹配类型为 @
            if (!messageSeg.typeOf(MessageDataEnum.AT)) {
                return false;
            }
            setOrderArgMatchedOut(
                    orderArgMatchedOut,
                    name,
                    contentType,
                    (AtData) messageSeg.getData()
            );
            return true;
        } else if (contentType.equals(YuniOrderArgContentTypeEnum.IMAGE)) {
            // 匹配类型为图片
            if (messageSeg.typeOf(MessageDataEnum.IMAGE)) {
                setOrderArgMatchedOut(
                        orderArgMatchedOut,
                        name,
                        contentType,
                        (ImageData) messageSeg.getData()
                );
                return true;
            } else if (messageSeg.typeOf(MessageDataEnum.TEXT)) {
                String imageText = ((TextData) messageSeg.getData()).getText();
                if (!isValidURL(imageText)) {
                    return false;
                }
                setOrderArgMatchedOut(
                        orderArgMatchedOut,
                        name,
                        contentType,
                        (TextData) messageSeg.getData()
                );
                return true;
            }
            return false;
        } else if (contentType.equals(YuniOrderArgContentTypeEnum.URL)) {
            // 匹配类型为 url
            if (!messageSeg.typeOf(MessageDataEnum.TEXT)) {
                return false;
            }
            String urlText = ((TextData) messageSeg.getData()).getText();
            if (!isValidURL(urlText)) {
                return false;
            }
            setOrderArgMatchedOut(
                    orderArgMatchedOut,
                    name,
                    contentType,
                    (TextData) messageSeg.getData()
            );
            return true;
        }
        return false;
    }

    private static void setOrderArgMatchedOut(OrderArgMatchedOut orderArgMatchedOut, String name, YuniOrderArgContentTypeEnum contentType, TextData textData) {
        orderArgMatchedOut.setName(name);
        orderArgMatchedOut.setContentType(contentType);
        orderArgMatchedOut.getData().setText(textData);
    }

    private static void setOrderArgMatchedOut(OrderArgMatchedOut orderArgMatchedOut, String name, YuniOrderArgContentTypeEnum contentType, AtData atData) {
        orderArgMatchedOut.setName(name);
        orderArgMatchedOut.setContentType(contentType);
        orderArgMatchedOut.getData().setAt(atData);
    }

    private static void setOrderArgMatchedOut(OrderArgMatchedOut orderArgMatchedOut, String name, YuniOrderArgContentTypeEnum contentType, ImageData imageData) {
        orderArgMatchedOut.setName(name);
        orderArgMatchedOut.setContentType(contentType);
        orderArgMatchedOut.getData().setImage(imageData);
    }

    public static boolean isValidURL(String urlString) {
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
