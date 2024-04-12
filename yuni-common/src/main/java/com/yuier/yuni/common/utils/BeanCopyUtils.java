package com.yuier.yuni.common.utils;

import cn.hutool.core.util.StrUtil;
import com.yuier.yuni.common.constants.SystemConstants;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/**
 * @Title: BeanCopyUtils
 * @Author yuier
 * @Package com.yuier.yuni.common.utils
 * @Date 2024/4/11 23:36
 * @description: Bean 拷贝工具类
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {

    }

    public static <S, T> T copyBean(S source, Class<T> targetClazz) {
        // 创建目标对象
        T target = null;
        try {
            target = targetClazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 返回结果
        return target;
    }

    public static <S, T> List<T> copyBeanList(List<S> listSource, Class<T> targetClazz) {
        return listSource.stream()
                .map(o -> copyBean(o, targetClazz))
                .toList();
    }

    public static <S, T> T copyEvent(S postEvent, Class<T> clazz) {
        T event = null;
        try {
            Class<?> oneBotPostEventDtoClass = postEvent.getClass();
            Field dataMapField = oneBotPostEventDtoClass.getDeclaredField(SystemConstants.POST_EVENT_DTO_FIELDS.EVENT_DATA);
            dataMapField.setAccessible(true);
            Map<String, Object> dataMap = (Map<String, Object>) dataMapField.get(postEvent);
            event = mapToMapObject(dataMap, clazz);
            copyFixedFields(postEvent, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    private static <S, T> void copyFixedFields(S postEvent, T event) {
        try {
            Class<?> postEventDtoClass = postEvent.getClass();
            Field[] fields = event.getClass().getDeclaredFields();
            copyOneField(postEvent, postEventDtoClass, event, fields, SystemConstants.POST_EVENT_DTO_FIELDS.TIME);
            copyOneField(postEvent, postEventDtoClass, event, fields, SystemConstants.POST_EVENT_DTO_FIELDS.SELF_ID);
            copyOneField(postEvent, postEventDtoClass, event, fields, SystemConstants.POST_EVENT_DTO_FIELDS.POST_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <S, T> void copyOneField(S postEvent, Class<?> postEventDtoClass, T event, Field[] eventFields, String postEventFieldName) {
        try {
            Field postEventField = postEventDtoClass.getDeclaredField(postEventFieldName);
            postEventField.setAccessible(true);
            Object postEventFieldValue = postEventField.get(postEvent);
            for (Field field : eventFields) {
                field.setAccessible(true);
                if (field.getName().equals(postEventFieldName)) {
                    field.set(event, postEventFieldValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T mapToMapObject(Map<String, Object> map, Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isPrivate(mod)) {
                    field.setAccessible(true);
                }
                Object value = map.get(StrUtil.toUnderlineCase(field.getName()));
                if (value != null) {
                    if (value instanceof Map) {
                        Class<?> targetType = field.getType();
                        field.set(obj, mapToMapObject((Map<String, Object>) value, targetType));
                    } else {
                        field.set(obj, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
