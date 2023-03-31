package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.vo.CourseBaseInfoVo;
import com.xuecheng.content.service.CourseBaseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: Lin
 * @since: 2023-03-29
 */
@Api(value = "课程信息接口",tags = "课程信息接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    private CourseBaseService courseBaseService;


    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(@ApiParam(name = "分页参数") PageParams pageParams,
                                       @ApiParam(name = "查询条件参数") @RequestBody QueryCourseParamsDto queryCourseParams) {
        return courseBaseService.getList(pageParams, queryCourseParams);
    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoVo createCourseBase(@RequestBody AddCourseDto addCourseDto) {
        // TODO 模拟当前公司id
        Long companyId = 1232141425L;
        return courseBaseService.createCourseBase(companyId, addCourseDto);
    }
}
