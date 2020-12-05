/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.utils;

import java.util.regex.Pattern;

/**
 * @author zhangwenxiang1
 * @date 2020/12/220:12
 */
public class RegexUtils {
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    public static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";
    public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
    public static final String REGEX_DOUBLE_BYTE_CHAR = "[^\\x00-\\xff]";
    public static final String REGEX_BLANK_LINE = "\\n\\s*\\r";
    public static final String REGEX_TENCENT_NUM = "[1-9][0-9]{4,}";
    public static final String REGEX_ZIP_CODE = "[1-9]\\d{5}(?!\\d)";
    public static final String REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$";
    public static final String REGEX_MATCH_SCALE_2 = "^([0-9]\\d*|[0-9]\\d*\\.\\d{1,2})$";
    public static final String REGEX_MATCH_SCALE_4 = "^([1-9]\\d*|[1-9]\\d*\\.\\d{1,4})$";
    public static final String REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$";
    public static final String REGEX_INTEGER = "^-?[1-9]\\d*$";
    public static final String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";
    public static final String REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$";
    public static final String REGEX_POSITIVE_FLOAT = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
    public static final String REGEX_NEGATIVE_FLOAT = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";
    public static final String REGEX_NOT_NEGATIVE_PLUS = "^[1-9]\\d*\\.?\\d*|0\\.?\\d*$";
    public static final String REGEX_NOT_WORDS = "^[a-zA-Z]+$";

    /**
     * 是否匹配正则表达式
     *
     * @param regex
     * @param input
     * @return
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 按照正则表达式切割字符串
     *
     * @param regex
     * @param input
     * @return
     */
    public static String[] getSplits(String regex, String input) {
        return input == null ? null : input.split(regex);
    }

    /**
     * 简单校验手机号
     *
     * @param input
     * @return
     */
    public static boolean isMobileSimple(CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 复杂匹配手机号
     *
     * @param input
     * @return
     */
    public static boolean isMobileExact(CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }

    /**
     * 是否是正整数
     *
     * @param input
     * @return
     */
    public static boolean isPositiveInteger(CharSequence input) {
        return isMatch(REGEX_POSITIVE_INTEGER, input);
    }

    public static boolean isMatchTwoScale(CharSequence input) {
        return isMatch(REGEX_MATCH_SCALE_2, input);
    }

    public static boolean isMatchFourScale(CharSequence input) {
        return isMatch(REGEX_MATCH_SCALE_4, input);
    }

    /**
     * 身份证15位校验
     *
     * @param input
     * @return
     */
    public static boolean isIDCard15(CharSequence input) {
        return isMatch(REGEX_ID_CARD15, input);
    }

    /**
     * 身份证18位校验
     *
     * @param input
     * @return
     */
    public static boolean isIDCard18(CharSequence input) {
        return isMatch(REGEX_ID_CARD18, input);
    }

    /**
     * 是否是Email
     *
     * @param input
     * @return
     */
    public static boolean isEmail(CharSequence input) {
        return isMatch(REGEX_EMAIL, input);
    }

    /**
     * 是否是URL
     *
     * @param input
     * @return
     */
    public static boolean isURL(CharSequence input) {
        return isMatch(REGEX_URL, input);
    }

    /**
     * 是否是日期类型
     *
     * @param input
     * @return
     */
    public static boolean isDate(CharSequence input) {
        return isMatch(REGEX_DATE, input);
    }

    /**
     * 是否是IP
     *
     * @param input
     * @return
     */
    public static boolean isIP(CharSequence input) {
        return isMatch(REGEX_IP, input);
    }
}
