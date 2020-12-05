/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 时间计算工具类
 *
 * @author zhangwenxiang1
 * @date 2020/12/215:25
 */
@Slf4j
public class DateUtils {

    public static final Integer COMMON_YEAR_DAYS = 365;
    public static final Integer LEAP_YEAR_DAYS = 366;

    public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final long TIMESTAMP_CONVERT_SIZE = 1000;


    /**
     * 转换时间格式 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        try {
            return DATE_FORMAT.format(date);
        } catch (Exception e) {
            log.warn("getFormatDate 时间格式转换失败，原时间：{}", date, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 转换时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getFormatDateFormat(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        try {
            return DATETIME_FORMAT.format(date);
        } catch (Exception e) {
            log.warn("getFormatDateFormat 时间格式转换失败，原时间：{}", date, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获取 timestamp
     *
     * @param date
     * @return
     */
    public static Long getTimestamp(Date date) {
        return date.getTime() / TIMESTAMP_CONVERT_SIZE;
    }

    /**
     * 时间转换 StringTo Date
     *
     * @param origin
     * @return
     */
    public static Date StringConvert2Date(String origin) {
        try {
            return DATETIME_FORMAT.parse(origin);
        } catch (ParseException e) {
            log.warn("StringConvert2Date 时间格式转换失败，原时间：{}", origin, e);
            return null;
        }
    }

    /**
     * 计算两个时间间隔（天）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer intervalTime(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            return null;
        }
        Date temp = startTime;
        if (compareTime(temp, endTime)) {
            startTime = endTime;
            endTime = temp;
        }

        int startTimeDays = getDaysByYear(startTime);
        int endTimeDays = getDaysByYear(endTime);
        int startTimeYear = getYear(startTime);
        int endTimeYear = getYear(endTime);
        int result = endTimeDays - startTimeDays;
        while (startTimeYear < endTimeYear) {
            if (checkLeapYear(startTimeYear++)) {
                result += DateUtils.LEAP_YEAR_DAYS;
            } else {
                result += DateUtils.COMMON_YEAR_DAYS;
            }
        }
        return result;
    }

    /**
     * 计算闰年平年
     *
     * @param year
     * @return
     */
    private static boolean checkLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 返回一年当中第几天,如果time为null,则返回null
     *
     * @param time
     * @return
     */
    public static Integer getDaysByYear(Date time) {
        Integer result = null;
        if (time != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            result = calendar.get(Calendar.DAY_OF_YEAR);
        }
        return result;
    }

    /**
     * 返回年份，如果time为null，则返回null
     *
     * @param time
     * @return int
     */
    public static Integer getYear(Date time) {
        Integer result = null;
        if (time != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            result = calendar.get(Calendar.YEAR);
        }
        return result;
    }

    /**
     * 拼接年月日
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date newDate(int year, int month, int day) {
        return new Date(year, month, day);
    }

    /**
     * 比较时间前后
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean compareTime(Date startTime, Date endTime) {
        return endTime.after(startTime);
    }

}
