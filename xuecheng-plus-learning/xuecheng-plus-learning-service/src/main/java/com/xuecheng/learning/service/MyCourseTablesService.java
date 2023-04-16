package com.xuecheng.learning.service;

import com.xuecheng.learning.model.dto.XcChooseCourseDto;
import com.xuecheng.learning.model.dto.XcCourseTablesDto;

/**
 * 我的课程相关服务
 * @author: Lin
 * @since: 2023-04-15
 */
public interface MyCourseTablesService {

    /**
     * 添加选课
     * @param userId 用户id
     * @param courseId 课程id
     * @return 结果
     */
    public XcChooseCourseDto addChooseCourse(String userId, Long courseId);

    /**
     * 判断学习资格
     * @param userId 用户id
     * @param courseId 课程id
     * @return 结果
     */
    public XcCourseTablesDto getLearningStatus(String userId, Long courseId);

    /**
     * 保存课程成功
     * @param choosecourseId 选择的课程id
     * @return 结果
     */
    boolean saveChooseCourseSuccess(String choosecourseId);
}

