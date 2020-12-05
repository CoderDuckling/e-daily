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
import com.zwx.edaily.common.facade.response.page.PageResult;
import lombok.Data;

/**
 * @author zhangwenxiang1
 * @date 2020/12/120:17
 */
@Data
public class BasePageResponse<T> {
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
    private PageResult<T> data;

    public BasePageResponse(BaseEnum enumCode, String message, PageResult<T> data) {
        this.code = enumCode.getCode();
        this.message = message;
        this.data = data;
    }

    public BasePageResponse() {
        this(BaseCodeEnum.SUCCESS, BaseCodeEnum.SUCCESS.getDesc());
    }

    public BasePageResponse(BaseEnum errorCode) {
        this(errorCode, errorCode.getDesc());
    }

    public BasePageResponse(PageResult<T> data) {
        this(BaseCodeEnum.SUCCESS, BaseCodeEnum.SUCCESS.getDesc(), data);
    }

    public BasePageResponse(BaseEnum errorCode, String message) {
        this(errorCode, message, null);
    }

    public static <T> BasePageResponse<T> success() {
        return new BasePageResponse();
    }

    public static <T> BasePageResponse<T> success(PageResult<T> data) {
        return new BasePageResponse(data);
    }

    public static <T> BasePageResponse<T> operationFailed() {
        return new BasePageResponse(BaseCodeEnum.OPERATION_FAILED);
    }

    public static <T> BasePageResponse<T> operationFailed(String message) {
        return new BasePageResponse(BaseCodeEnum.OPERATION_FAILED, message);
    }

    public static <T> BasePageResponse<T> operationFailed(PageResult<T> data) {
        return new BasePageResponse(BaseCodeEnum.OPERATION_FAILED, BaseCodeEnum.OPERATION_FAILED.getDesc(), data);
    }

    public static <T> BasePageResponse<T> operationFailed(String message, PageResult<T> data) {
        return new BasePageResponse(BaseCodeEnum.OPERATION_FAILED, message, data);
    }

    public static <T> BasePageResponse<T> systemError() {
        return new BasePageResponse(BaseCodeEnum.SYSTEM_ERROR);
    }

    public static <T> BasePageResponse<T> systemError(String message) {
        return new BasePageResponse(BaseCodeEnum.SYSTEM_ERROR, message);
    }

    public static <T> BasePageResponse<T> systemError(PageResult<T> data) {
        return new BasePageResponse(BaseCodeEnum.SYSTEM_ERROR, BaseCodeEnum.SYSTEM_ERROR.getDesc(), data);
    }

    public static <T> BasePageResponse<T> systemError(String message, PageResult<T> data) {
        return new BasePageResponse(BaseCodeEnum.SYSTEM_ERROR, message, data);
    }
}
