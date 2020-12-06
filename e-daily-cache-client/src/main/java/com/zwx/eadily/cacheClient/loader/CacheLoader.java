/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.eadily.cacheClient.loader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author 张文祥
 * @date 2020/12/517:24
 */
@FunctionalInterface
public interface CacheLoader<K,V> extends Function<K,V> {
    V load(K key) throws Throwable;

    /**
     * 加载集合所有key
     * @param keys
     * @return
     * @throws Throwable
     */
    default Map<K, V> loadAll(Set<K> keys) throws Throwable {
        Map<K, V> map = new HashMap();
        Iterator var3 = keys.iterator();

        while(var3.hasNext()) {
            K k = (K) var3.next();
            V value = this.load(k);
            if (value != null) {
                map.put(k, value);
            }
        }
        return map;
    }

    /**
     * 加载单个key
     * @param key
     * @return
     */
    default V apply(K key) {
        try {
            return this.load(key);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
