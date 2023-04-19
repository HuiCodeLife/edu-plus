package com.xuecheng.content.api;

import com.alibaba.fastjson.JSON;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.model.vo.CourseBaseInfoVo;
import com.xuecheng.content.model.vo.CoursePreviewVo;
import com.xuecheng.content.model.vo.TeachplanVo;
import com.xuecheng.content.service.CoursePublishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: Lin
 * @since: 2023-04-11
 */
@Controller
public class CoursePublishController {

    @Autowired
    private CoursePublishService coursePublishService;

    /**
     * 预览课程信息
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("预览课程信息")
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId){
        //获取课程预览信息
        CoursePreviewVo coursePreviewInfo = coursePublishService.getCoursePreviewInfo(courseId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model",coursePreviewInfo);
        modelAndView.setViewName("course_template");
        return modelAndView;
    }


    @ApiOperation("课程提交审核")

    @ResponseBody
    @PostMapping("/courseaudit/commit/{courseId}")
    public void commitAudit(@PathVariable("courseId") Long courseId){
        Long companyId = 1232141425L;
        coursePublishService.commitAudit(companyId,courseId);
    }

    @ApiOperation("课程发布")
    @ResponseBody
    @PostMapping ("/coursepublish/{courseId}")
    public void coursepublish(@PathVariable("courseId") Long courseId){
        Long companyId = 1232141425L;
        coursePublishService.publish(companyId,courseId);

    }

    @ApiOperation("查询课程发布信息")
    @ResponseBody
    @GetMapping("/r/coursepublish/{courseId}")
    public CoursePublish getCoursepublish(@PathVariable("courseId") Long courseId) {
        CoursePublish coursePublish = coursePublishService.getCoursePublish(courseId);
        return coursePublish;
    }


    @ApiOperation("获取课程发布信息")
    @ResponseBody
    @GetMapping("/course/whole/{courseId}")
    public CoursePreviewVo getCoursePublish(@PathVariable("courseId") Long courseId) {
        //查询课程发布信息
//        CoursePublish coursePublish = coursePublishService.getCoursePublish(courseId);

        // 添加缓存查询
        CoursePublish coursePublish = coursePublishService.getCoursePublishCache(courseId);

        // 课程不存在
        if (coursePublish == null) {
            return new CoursePreviewVo();
        }
        //课程基本信息
        CourseBaseInfoVo courseBase = new CourseBaseInfoVo();
        BeanUtils.copyProperties(coursePublish, courseBase);
        //课程计划
        List<TeachplanVo> teachplans = JSON.parseArray(coursePublish.getTeachplan(), TeachplanVo.class);
        CoursePreviewVo coursePreviewInfo = new CoursePreviewVo();
        coursePreviewInfo.setCourseBase(courseBase);
        coursePreviewInfo.setTeachplans(teachplans);
        return coursePreviewInfo;
    }

}
