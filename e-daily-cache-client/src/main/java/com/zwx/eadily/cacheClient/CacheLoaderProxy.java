/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.eadily.cacheClient;

import com.zwx.eadily.cacheClient.loader.CacheLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author 张文祥
 * @date 2020/12/615:56
 */
@Slf4j
@Component
public class CacheLoaderProxy  {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private Executor executor;

    /**
     * 获取缓存，如果存在就获取，不存在就调用方法，并且回写到缓存
     * @param key
     * @param function
     * @param <K>
     * @param <R>
     * @return
     */
    public <K,R> R computeIfAbsent(String key, Function<K,R> function, long time, TimeUnit timeUnit){
        if (redisUtils.hasKey(key)){
            return (R) redisUtils.get(key);
        }
        log.info("computeIfAbsent 缓存数据不存在，调用方法获取数据,function:{}",function.toString());
        R apply = function.apply((K) key);
        executor.execute(() -> redisUtils.set(key,apply,time,timeUnit));
        return apply;
    }
}
