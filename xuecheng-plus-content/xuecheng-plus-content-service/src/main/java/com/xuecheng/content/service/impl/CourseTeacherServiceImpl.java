package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程-教师关系表 服务实现类
 * </p>
 *
 * @author Lin
 */
@Slf4j
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {

    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    @Override
    public CourseTeacher saveTeacher(CourseTeacher courseTeacher) {

        Long id = courseTeacher.getId();
        if (id == null) {
            courseTeacherMapper.insert(courseTeacher);
        }else {
            courseTeacherMapper.updateById(courseTeacher);
        }

        return courseTeacher;
    }

    @Override
    public void deleteCourseTeacher(Long courseId, Long teacherId) {
        courseTeacherMapper.delete(new LambdaQueryWrapper<CourseTeacher>().eq(CourseTeacher::getCourseId,courseId).eq(CourseTeacher::getId,teacherId));
    }
}
