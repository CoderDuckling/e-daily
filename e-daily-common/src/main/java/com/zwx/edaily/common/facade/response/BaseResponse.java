/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.facade.response;

import com.zwx.edaily.common.enums.BaseCodeEnum;
import com.zwx.edaily.common.enums.BaseEnum;
import lombok.Data;

/**
 * 基础结果返回
 *
 * @author zhangwenxiang1
 * @date 2020/12/120:04
 */
@Data
public class BaseResponse<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 具体数据
     */
    private T data;

    public BaseResponse(BaseEnum enumCode, String message, T data) {
        this.code = enumCode.getCode();
        this.message = message;
        this.data = data;
    }

    public BaseResponse() {
        this(BaseCodeEnum.SUCCESS, BaseCodeEnum.SUCCESS.getDesc());
    }

    public BaseResponse(BaseEnum errorCode) {
        this(errorCode, errorCode.getDesc());
    }

    public BaseResponse(T data) {
        this(BaseCodeEnum.SUCCESS, BaseCodeEnum.SUCCESS.getDesc(), data);
    }

    public BaseResponse(BaseEnum errorCode, String message) {
        this(errorCode, message, null);
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse();
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse(data);
    }

    public static <T> BaseResponse<T> operationFailed() {
        return new BaseResponse(BaseCodeEnum.OPERATION_FAILED);
    }

    public static <T> BaseResponse<T> operationFailed(String message) {
        return new BaseResponse(BaseCodeEnum.OPERATION_FAILED, message);
    }

    public static <T> BaseResponse<T> operationFailed(T data) {
        return new BaseResponse(BaseCodeEnum.OPERATION_FAILED, BaseCodeEnum.OPERATION_FAILED.getDesc(), data);
    }

    public static <T> BaseResponse<T> operationFailed(String message, T data) {
        return new BaseResponse(BaseCodeEnum.OPERATION_FAILED, message, data);
    }

    public static <T> BaseResponse<T> systemError() {
        return new BaseResponse(BaseCodeEnum.SYSTEM_ERROR);
    }

    public static <T> BaseResponse<T> systemError(String message) {
        return new BaseResponse(BaseCodeEnum.SYSTEM_ERROR, message);
    }

    public static <T> BaseResponse<T> systemError(T data) {
        return new BaseResponse(BaseCodeEnum.SYSTEM_ERROR, BaseCodeEnum.SYSTEM_ERROR.getDesc(), data);
    }

    public static <T> BaseResponse<T> systemError(String message, T data) {
        return new BaseResponse(BaseCodeEnum.SYSTEM_ERROR, message, data);
    }
}
