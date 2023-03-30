package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 课程查询参数
 * @author: Lin
 * @since: 2023-03-29
 */
@ApiModel(description = "查询条件参数模型")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryCourseParamsDto {
    /**
     * 审核状态
     */
    private String auditStatus;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 发布状态
     */
    private String publishStatus;

}
