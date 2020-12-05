/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.exception;

import com.zwx.edaily.common.enums.BaseEnum;
import lombok.Data;

/**
 * 基础异常类
 *
 * @author zhangwenxiang1
 * @date 2020/12/120:32
 */
@Data
public class BaseException {
    /**
     * 异常状态码
     */
    private BaseEnum errorCode;
    /**
     * 异常信息
     */
    private String errorMessage;
}
