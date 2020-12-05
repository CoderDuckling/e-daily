/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

/**
 * @author zhangwenxiang1
 * @date 2020/12/218:33
 */
@Slf4j
public class CollectionUtil {

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断集合是否为空或者Null
     *
     * @param collection
     * @return
     */
    public static boolean isNullOrEmpty(Collection<? extends Object> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否不为空或Null
     *
     * @param collection
     * @return
     */
    public static boolean isNotNullOrEmpty(Collection<? extends Object> collection) {
        return !isNullOrEmpty(collection);
    }

    /**
     * 判断Map是否为null或空
     *
     * @param map
     * @return
     */
    public static boolean isNullOrEmpty(Map<? extends Object, ? extends Object> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断Mapbu为null或空
     *
     * @param map
     * @return
     */
    public static boolean isNotNullOrEmpty(Map<? extends Object, ? extends Object> map) {
        return !isNullOrEmpty(map);
    }

    /**
     * JSON转Map
     *
     * @param text
     * @return
     */
    public static Map json2Map(String text) {
        try {
            return (Map) JSON.parse(text);
        } catch (Exception e) {
            log.warn("json2Map json转换Map格式失败，参数：{}", text, e);
        }
        return null;
    }

    /***
     * Map转JSON
     *
     * @param map
     * @return
     */
    public static String map2Json(Map<String, Object> map) {
        return new JSONObject(map).toJSONString();
    }

}
