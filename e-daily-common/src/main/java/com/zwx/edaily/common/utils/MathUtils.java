/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.utils;

import java.text.DecimalFormat;

/**
 * @author zhangwenxiang1
 * @date 2020/12/215:40
 */
public class MathUtils {

    public static final Integer ONE_HUNDRED = 100;

    /**
     * 抹去百位数字（四舍五入）
     *
     * @param originalValue
     * @return
     */
    public static int roundAndRemoveHundred(int originalValue) {
        int tempValue = originalValue % ONE_HUNDRED;
        int result = originalValue - tempValue;
        if (tempValue >= ONE_HUNDRED / 2) {
            result = result + ONE_HUNDRED;
        }
        return result;
    }

    /**
     * 计算除法(返回小数)
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static String divide(int divisor, int dividend) {
        DecimalFormat divide = new DecimalFormat("0.0000");
        return divide.format((float) divisor / dividend);
    }

    /**
     * 计算乘法
     *
     * @param param1
     * @param param2
     * @return
     */
    public static float multiply(String param1, String param2) {
        return Float.parseFloat(param1) * Float.parseFloat(param2);
    }
}
