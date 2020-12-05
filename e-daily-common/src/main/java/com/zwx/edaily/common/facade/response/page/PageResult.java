/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.zwx.edaily.common.facade.response.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页返回结果定义
 *
 * @author zhangwenxiang1
 * @date 2020/12/120:10
 */
@Data
@ApiModel(description = "分页结果")
public class PageResult<T> {
    @ApiModelProperty("当前页")
    @JsonIgnore
    private int pageNum;
    @ApiModelProperty("每页数量")
    @JsonIgnore
    private int pageSize;
    @ApiModelProperty("总条数")
    private long total;
    @ApiModelProperty("总页数")
    private int pageCount;
    @ApiModelProperty("当前数据Index")
    private int currentIndex;
    @ApiModelProperty("是否还有数据")
    private boolean hasNext;
    @ApiModelProperty("业务数据")
    private List<T> results;
}
