/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.eadily.cacheClient.bootstrap;

import com.zwx.eadily.cacheClient.RedisUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张文祥
 * @date 2020/12/515:19
 */
@SpringBootApplication
public class StartMain {
    public static void main(String[] args) {
        SpringApplication.run(StartMain.class, args);
        RedisUtils redisUtils = new RedisUtils();
        redisUtils.set("zwx", "张文祥", 1000);
        System.out.println(redisUtils.getExpire("zwx"));
        System.out.println(redisUtils.get("zwx"));
    }
}
