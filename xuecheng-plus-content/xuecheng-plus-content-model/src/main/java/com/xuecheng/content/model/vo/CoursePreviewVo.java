package com.xuecheng.content.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 课程预览数据模型
 * @author: Lin
 * @since: 2023-04-11
 */
@Data
@ToString
public class CoursePreviewVo {

    /**
     * 课程基本信息,课程营销信息
     */
    CourseBaseInfoVo courseBase;


    /**
     * 课程计划信息
     */
    List<TeachplanVo> teachplans;

}
