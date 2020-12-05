/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author 张文祥
 * @date 2020/12/515:31
 */
@Configuration
@EnableAsync
@EnableConfigurationProperties(ExecutorProperties.class)
public class ExecutorConfig {
    private static final Logger log = LoggerFactory.getLogger(ExecutorConfig.class);
    @Autowired
    private ExecutorProperties executorProperties;

    public static final int awaitTerminationSeconds = 30;

    public ExecutorConfig() {
    }

    @Bean({"taskExecutor"})
    @Primary
    public Executor taskExecutor() {
        return this.buildExecutor(this.executorProperties);
    }


    private Executor buildExecutor(ExecutorBaseProperties executorProperties) {
        ExecutorConfig.TuhuThreadPoolTaskExecutor executor = new ExecutorConfig.TuhuThreadPoolTaskExecutor();
        executor.setCorePoolSize(executorProperties.getCorePoolSize());
        executor.setMaxPoolSize(executorProperties.getMaxPoolSize());
        executor.setQueueCapacity(executorProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(executorProperties.getKeepAliveSeconds());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(30);
        executor.setThreadNamePrefix(executorProperties.getThreadNamePrefix());
        RejectedExecutionHandler rejectedExecutionHandler = null;

        try {
            Class<?> clazz = Class.forName("java.util.concurrent.ThreadPoolExecutor$" + executorProperties.getRejectionPolicyName());
            rejectedExecutionHandler = (RejectedExecutionHandler) clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException var5) {
            log.error("获取rejection-policy异常，请查看配置文件", var5);
            return null;
        }

        executor.setRejectedExecutionHandler(rejectedExecutionHandler);
        executor.initialize();
        return TtlExecutors.getTtlExecutor(executor);
    }

    public class TuhuThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
        public TuhuThreadPoolTaskExecutor() {
        }

        public void execute(Runnable runnable) {
            Map<String, String> context = MDC.getCopyOfContextMap();
            super.execute(() -> {
                this.run(runnable, context);
            });
        }

        private void run(Runnable runnable, Map<String, String> context) {
            if (context != null) {
                MDC.setContextMap(context);
            }
            runnable.run();
        }
    }
}
