package com.xuecheng.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 分页参数
 * @author: Lin
 * @since: 2023-03-29
 */
@ApiModel(description = "页面参数模型")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    /**
     * 当前页
     */
    private Long pageNo = 1L;

    /**
     * 每页记录数
     */
    private Long pageSize = 30L;
}
