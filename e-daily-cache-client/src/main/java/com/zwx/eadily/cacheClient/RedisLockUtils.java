/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.eadily.cacheClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 张文祥
 * @date 2020/12/615:31
 */
@Slf4j
@Component
public class RedisLockUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 加锁设置，默认5秒
     *
     * @param key
     * @return
     */
    public boolean tryLock(String key) {
        UUID uuid = UUID.randomUUID();
        try {
            redisTemplate.opsForValue().setIfAbsent(key, uuid);
            redisUtils.expire(key);
            return true;
        } catch (Exception e) {
            log.warn("tryLock 加锁失败，key:{}", key, e);
        }
        return false;
    }

    /**
     * 指定时间加锁
     *
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean tryLock(String key, long time, TimeUnit timeUnit) {
        UUID uuid = UUID.randomUUID();
        try {
            redisTemplate.opsForValue().setIfAbsent(key, uuid);
            redisUtils.expire(key, time, timeUnit);
            return true;
        } catch (Exception e) {
            log.warn("tryLock 加锁失败，key:{}", key, e);
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @return
     */
    public boolean unLock(String key) {
        try {
            if (redisUtils.getExpire(key) == 0L){
                log.warn("unLock 解锁失败,加锁时间已失效，key:{}", key);
                return false;
            }
            if (redisUtils.hasKey(key)) {
                redisTemplate.opsForValue().getOperations().delete(key);
                return true;
            }
        } catch (Exception e) {
            log.warn("unLock 解锁失败，key:{}", key, e);
        }
        return false;
    }
}
