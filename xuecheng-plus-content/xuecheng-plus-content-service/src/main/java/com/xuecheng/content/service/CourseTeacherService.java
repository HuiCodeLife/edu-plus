package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;

/**
 * <p>
 * 课程-教师关系表 服务类
 * </p>
 *
 * @author Lin
 * @since 2023-03-29
 */
public interface CourseTeacherService extends IService<CourseTeacher> {

    /**
     * 添加讲师
     * @param courseTeacher 讲师信息
     * @return 结果
     */
    CourseTeacher saveTeacher(CourseTeacher courseTeacher);

    /**
     * 删除课程讲师
     * @param courseId 课程id
     * @param teacherId 讲师id
     */
    void deleteCourseTeacher(Long courseId, Long teacherId);
}
