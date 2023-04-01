package com.xuecheng.content.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Lin
 * @since: 2023-04-01
 */
@Api(value = "课程讲师接口", tags = "课程讲师接口")
@RestController
public class CourseTeacherController {

    @Autowired
    private CourseTeacherService courseTeacherService;

    @ApiOperation("查询课程讲师")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> list(@PathVariable Long courseId) {
        return courseTeacherService.list(new LambdaQueryWrapper<CourseTeacher>().eq(CourseTeacher::getCourseId, courseId));
    }

    @ApiOperation("添加课程讲师")
    @PostMapping("/courseTeacher")
    public CourseTeacher list(@RequestBody CourseTeacher courseTeacher) {
        return courseTeacherService.saveTeacher(courseTeacher);
    }


    @ApiOperation("删除课程讲师")
    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}")
    public void list(@PathVariable Long courseId,@PathVariable Long teacherId) {
        courseTeacherService.deleteCourseTeacher(courseId,teacherId);
    }




}
