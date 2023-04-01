package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.vo.CourseBaseInfoVo;

import java.util.List;

/**
 * <p>
 * 课程基本信息 服务类
 * </p>
 *
 * @author Lin
 * @since 2023-03-29
 */
public interface CourseBaseService extends IService<CourseBase> {

    /**
     * 获取课程信息列表
     * @param pageParams 分页参数
     * @param queryCourseParams 查询条件
     * @return 结果
     */
    PageResult<CourseBase> getList(PageParams pageParams, QueryCourseParamsDto queryCourseParams);


    /**
     * 创建课程
     * @param companyId 机构id
     * @param addCourseDto 课程参数
     * @return 结果
     */
    CourseBaseInfoVo createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /**
     * 返回结果
     * @param courseId 课程id
     * @return 结果
     */
    CourseBaseInfoVo getCourseBaseInfo(Long courseId);


    /**
     * 修改课程信息
     * @param companyId  机构id
     * @param dto 参数
     * @return 结果
     */
    CourseBaseInfoVo updateCourseBase(Long companyId, EditCourseDto dto);

    /**
     * 删除课程
     * @param courseId 课程id
     */
    void deleteCouserBaseInfo(Long courseId);

}
