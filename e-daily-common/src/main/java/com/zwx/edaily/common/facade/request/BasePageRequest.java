/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.facade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求参数
 *
 * @author zhangwenxiang1
 * @date 2020/12/120:19
 */
@Data
@ApiModel(description = "分页参数请求")
public class BasePageRequest {
    @ApiModelProperty("当前列表第几项")
    private Integer currentIndex = 0;
    @ApiModelProperty("第几页")
    private Integer page = 1;
    @ApiModelProperty("每页数据")
    private Integer pageSize = 10;
}
