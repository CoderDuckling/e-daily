/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.eadily.cacheClient;

import com.zwx.edaily.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author 张文祥
 * @date 2020/12/515:07
 */
@Slf4j
@Component
public class RedisUtils {

    public static final long DEFAULT_CACHE_TIME = 5*1000;

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
    public boolean expire(String key, long time,TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            log.warn("expire 设置缓存失效失效失败，key:{},time:{}", key, time, e);
        }
        return false;
    }

    /**
     * 默认key过期时间为5秒
     * @param key
     * @return
     */
    public boolean expire(String key){
        return expire(key,DEFAULT_CACHE_TIME,TimeUnit.SECONDS);
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
    public long getExpire(String key,TimeUnit timeUnit) {
        return redisTemplate.getExpire(key,timeUnit);
    }

    /**
     * 获取缓存的过期时间(秒)
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        if (hasKey(key)){
            return redisTemplate.getExpire(key,TimeUnit.SECONDS);
        }
        return 0L;
    }

    /**
     * 数据放入缓存
     *
     * @param key
     * @param object
     * @param time
     * @return
     */
    public boolean set(String key, Object object, long time, TimeUnit timeUnit) {
        if (timeUnit == null) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, object, Math.abs(time), timeUnit);
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
     * 批量设置hash结构
     *
     * @param key
     * @param map
     * @param time
     * @param timeUnit
     */
    public void multiSetHash(String key, Map<String, String> map, long time, TimeUnit timeUnit) {
        if (time < 0 || timeUnit == null) {
            return;
        }
        try {
            redisTemplate.opsForHash().putAll(key, map);
            redisTemplate.expire(key, time, timeUnit);
        } catch (Exception e) {
            log.warn("multiSetHash 集合操作失败，key:{}，collection：:{}", key, map, e);
        }

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
     * 批量删除缓存
     *
     * @param keys
     */
    public void delete(Collection<String> keys) {
        try {
            executor.execute(() -> redisTemplate.delete(keys));
        } catch (Exception e) {
            log.warn("delete 删除集合缓存失败，key:{}", keys, e);
        }
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

    /**
     * 根据key获取对应的value
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 批量获取缓存hash中的内容
     *
     * @param key
     * @param subKeys
     * @return
     */
    public List<Object> mutilGetHash(String key, List<String> subKeys) {
        List<Object> result = new ArrayList<>();
        try {
            result = redisTemplate.opsForHash().multiGet(key, Collections.singleton(subKeys));
            return result;
        } catch (Exception e) {
            log.warn("mutilGetHash 批量获取缓存失败，key:{},List:{}", key, subKeys, e);
        }
        return result;
    }

    /**
     * 判断是否存在String
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        if(!StringUtils.isNullOrEmpty(key)){
            return redisTemplate.hasKey(key);
        }
        return false;
    }

    /**
     * 缓存数据增加
     * @param key
     * @param delta
     * @return
     */
    public boolean incr(String key,long delta){
        if(hasKey(key)){
            try{
                redisTemplate.opsForValue().increment(key,delta);
                return true;
            }catch (Exception e){
                log.warn("incr 缓存key增加是失败，key:{},number:{}", key, delta, e);
            }

        }
        return false;
    }

    /**
     * 增1操作
     * @param key
     * @return
     */
    public boolean incr(String key){
        return incr(key,1);
    }

    /**
     * 减1操作
     * @param key
     * @return
     */
    public boolean decr(String key){
        return incr(key,-1);
    }

    /**
     * 获取缓存中的set集合
     * @param key
     * @return
     */
    public Set<Object> getSet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 设置Set集合
     * @param key
     * @param time
     * @param timeUnit
     * @param objects
     * @return
     */
    public boolean setSet(String key, long time, TimeUnit timeUnit, Object... objects){
        if (timeUnit == null){
            return false;
        }
        try {
            redisTemplate.opsForSet().add(key, objects);
            expire(key, time, timeUnit);
            return true;
        }catch (Exception e){
            log.warn("setSet 缓存key增加是失败，key:{},objects:{}", key, objects, e);
        }
        return false;
    }

    /**
     * 判断一个object是否再Set中
     * @param key
     * @param object
     * @return
     */
    public boolean isMember(String key,Object object){
        return redisTemplate.opsForSet().isMember(key,object);
    }

    /**
     * 判断Set集合大小
     * @param key
     * @return
     */
    public long size(String key){
        return redisTemplate.opsForSet().size(key);
    }

}
