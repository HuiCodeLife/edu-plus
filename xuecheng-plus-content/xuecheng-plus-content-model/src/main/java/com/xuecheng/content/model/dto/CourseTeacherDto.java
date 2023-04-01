package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Lin
 * @since: 2023-04-01
 */
@Data
@ToString
public class CourseTeacherDto {


    @NotEmpty(message = "课程标识不能为空")
    @ApiModelProperty(value = "课程标识", required = true)
    private Long id;
    /**
     * 课程标识
     */
    @NotEmpty(message = "课程标识不能为空")
    @ApiModelProperty(value = "课程标识", required = true)
    private Long courseId;

    /**
     * 教师标识
     */
    @NotEmpty(message = "教师标识不能为空")
    @ApiModelProperty(value = "教师标识", required = true)
    private String teacherName;

    /**
     * 教师职位
     */
    @NotEmpty(message = "教师职位不能为空")
    @ApiModelProperty(value = "教师职位", required = true)
    private String position;

    /**
     * 教师简介
     */
    @NotEmpty(message = "教师简介不能为空")
    @ApiModelProperty(value = "教师简介", required = true)
    private String introduction;
}
