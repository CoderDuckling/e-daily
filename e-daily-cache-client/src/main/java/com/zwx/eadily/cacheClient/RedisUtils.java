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

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author 张文祥
 * @date 2020/12/515:07
 */
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private Executor executor;

    /**
     * 指定缓存的失效时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.warn("expire 设置缓存失效失效失败，key:{},time:{}", key, time, e);
        }
        return false;
    }

    /**
     * 异步指定缓存的失效时间
     *
     * @param key
     * @param time
     * @return
     */
    public void EXPIRE(String key, long time) {
        try {
            if (time > 0) {
                executor.execute(() -> redisTemplate.expire(key, time, TimeUnit.SECONDS));
            }
        } catch (Exception e) {
            log.warn("expire 设置缓存失效失效失败，key:{},time:{}", key, time, e);
        }
    }

    /**
     * 获取缓存的过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 数据放入缓存
     *
     * @param key
     * @param object
     * @param time
     * @return
     */
    public boolean set(String key, Object object, long time) {
        try {
            redisTemplate.opsForValue().set(key, object, Math.abs(time), TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.warn("set 设置缓存失效失败，key:{},time:{}", key, time, e);
        }
        return false;
    }

    /**
     * 数据永久放入缓存
     *
     * @param key
     * @param object
     * @return
     */
    public boolean setEver(String key, Object object) {
        try {
            redisTemplate.opsForValue().set(key, object);
            return true;
        } catch (Exception e) {
            log.warn("setEver 永久放入缓存失败，key:{}", key, e);
        }
        return false;
    }

    /**
     * 异步保存数据至缓存
     *
     * @param key
     * @param object
     * @param time
     */
    public void SET(String key, Object object, long time) {
        try {
            executor.execute(() -> redisTemplate.opsForValue().set(key, object, Math.abs(time), TimeUnit.SECONDS));
        } catch (Exception e) {
            log.warn("SET 异步设置缓存失败，key:{},time:{}", key, time, e);
        }
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public boolean delete(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            log.warn("delete 删除缓存失败，key:{}", key, e);
        }
        return false;
    }

    /**
     * 异步删除缓存
     *
     * @param key
     * @return
     */
    public void DELETE(String key) {
        try {
            executor.execute(() -> redisTemplate.delete(key));
        } catch (Exception e) {
            log.warn("DELETE 异步删除缓存失败，key:{}", key, e);
        }
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
