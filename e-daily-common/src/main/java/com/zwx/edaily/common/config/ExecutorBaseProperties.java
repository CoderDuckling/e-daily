/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.config;

import lombok.Data;

/**
 * @author 张文祥
 * @date 2020/12/515:35
 */
@Data
public class ExecutorBaseProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private int keepAliveSeconds;
    private String threadNamePrefix;
    private String rejectionPolicyName;
}
