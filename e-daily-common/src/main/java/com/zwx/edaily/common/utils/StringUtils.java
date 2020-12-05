/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author zhangwenxiang1
 * @date 2020/12/216:09
 */
@Slf4j
public class StringUtils {

    /**
     * 判断字符串是否为null或者为空
     *
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * 判断两个字符串是否相同（排除为空为null情况）
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equals(String source, String target) {
        if (isNullOrEmpty(source) || isNullOrEmpty(target)) {
            return false;
        }
        return source.equals(target);
    }

    /**
     * 根据目标字符串生成对应的UUID
     *
     * @param target
     * @return
     */
    public static String string2MD5(String target) {
        return isBlank(target) ? UUID.randomUUID().toString() : UUID.nameUUIDFromBytes(target.getBytes()).toString();
    }

    /**
     * 包装isBlank方法
     *
     * @param target
     * @return
     */
    public static boolean isBlank(String target) {
        return org.apache.commons.lang3.StringUtils.isBlank(target);
    }

    /**
     * 判断String是否是JOSN格式
     *
     * @param target
     * @return
     */
    public static boolean isJson(String target) {
        try {
            JSON.parse(target);
        } catch (Exception e) {
            log.warn("isJson 转换失败，数据不是Json格式，参数：{}",target,e);
            return false;
        }
        return true;
    }

    /**
     * 获取大写字符串
     * @param target
     * @return
     */
    public static String getUpperCase(String target){
        return target.toUpperCase();
    }

    /**
     * 获取小写字符串
     * @param target
     * @return
     */
    public static String getLowerCase(String target){
        return target.toLowerCase();
    }

}
