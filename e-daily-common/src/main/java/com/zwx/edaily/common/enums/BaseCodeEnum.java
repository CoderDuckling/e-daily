/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.enums;

/**
 * 基础枚举返回定义
 *
 * @author zhangwenxiang1
 * @date 2020/12/120:23
 */
public enum BaseCodeEnum implements BaseEnum {
    SUCCESS(10000, "SUCCESS", "操作成功"),
    //
    URL_REQUEST_ERROR(10001, "url.request.error", "异常接口调用"),
    REQUEST_ERROR(10001, "REQUEST_ERROR", "异常接口调用"),
    PROCESS_FAIL(10002, "PROCESS_FAIL", "服务器处理失败"),
    TOO_MANY_REQUEST(10003, "TOO_MANY_REQUEST", "访问过于频繁"),
    PERMISSION_DENY(10004, "PERMISSION_DENY", "用户服务无权限"),
    AUTHENTICATION_EXPIRED(10005, "AUTHENTICATION_EXPIRED", "身份认证过期"),
    SUCCESS_EXIST(10006, "SUCCESS_EXIST", "记录已存在"),

    PARAM_ERROR(10007, "PARAM_ERROR", "参数错误"),
    PARAM_IS_NULL(10008, "PARAM_IS_NULL", "参数为空"),
    INSUFFICIENT_USER_PERMISSIONS(10009, "INSUFFICIENT_USER_PERMISSIONS", "用户权限不足"),
    USER_DOES_NOT_EXISTS(10010, "USER_DOES_NOT_EXISTS", "用户不存在"),
    CONTENT_IS_NULL(10011, "CONTENT_IS_NULL", "内容为空"),
    CONTENT_IS_ILLEGAL(10012, "CONTENT_IS_ILLEGAL ", "包含非法内容"),
    PHONE_NUMBER_HAS_BEEN_USED(10013, "PHONE_NUMBER_HAS_BEEN_USED", "该手机号已经被使用"),

    AUTH_FAILD(10014, "AUTH_FAILD", "验证失败"),
    AUTH_FAILED(10015, "AUTH_FAILED", "验证失败"),
    ILLEGAL_OPERATION(10016, "ILLEGAL_OPERATION", "非法操作"),
    USERNAME_OR_PASSWORD_ERROR(10017, "USERNAME_OR_PASSWORD_ERROR", "用户名或者密码错误"),
    OPERATION_FAILED(10018, "OPERATION_FAILED", "操作失败"),
    SYSTEM_ERROR(10019, "SYSTEM_ERROR", "系统异常"),

    CALLSERVICCE_ERROR(10020, "CALLSERVICCE_ERROR", "调用服务异常"),
    CALL_SERVICE_ERROR(10021, "CALL_SERVICE_ERROR", "调用服务异常"),
    SUCCESS_NOT_EXIST(10022, "SUCCESS_NOT_EXIST", "数据不存在"),
    REPEAT_OPERATION(10023, "REPEAT_OPERATION", "重复操作");

    private Integer code;
    private String name;
    private String desc;

    BaseCodeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }
}
