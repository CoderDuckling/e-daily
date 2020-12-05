/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.enums;

/**
 * @author zhangwenxiang1
 * @date 2020/12/120:24
 */
public interface BaseEnum {
    /**
     * 获取枚举状态码
     * @return
     */
    Integer getCode();

    /**
     * 获取枚举名称
     * @return
     */
    String getName();

    /**
     * 获取枚举描述
     * @return
     */
    String getDesc();
}
