/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 张文祥
 * @date 2020/12/515:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "executor.conf")
public class ExecutorProperties extends ExecutorBaseProperties {
    private int corePoolSize = 10;
    private int maxPoolSize = 20;
    private int queueCapacity = 200;
    private int keepAliveSeconds = 60;
    private String threadNamePrefix = "taskExecutor-";
    private String rejectionPolicyName = "CallerRunsPolicy";

}
