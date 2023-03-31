package com.xuecheng;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * CourseBaseMapper测试类
 *
 * @author: Lin
 * @since: 2023-03-30
 */

@SpringBootTest
public class CourseBaseMapperTests {

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(74L);
        Assertions.assertNotNull(courseBase);

        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();

        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");
        queryCourseParamsDto.setAuditStatus("202004");
        queryCourseParamsDto.setPublishStatus("203001");


        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),
                CourseBase::getName,
                queryCourseParamsDto.getCourseName());
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),
                CourseBase::getAuditStatus,
                queryCourseParamsDto.getAuditStatus());
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);//页码
        pageParams.setPageSize(3L);//每页记录数
        IPage<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        IPage<CourseBase> courseBaseIPage = courseBaseMapper.selectPage(page, queryWrapper);
        long current = courseBaseIPage.getCurrent();
        long pages = courseBaseIPage.getPages();
        long size = courseBaseIPage.getSize();
        long total = courseBaseIPage.getTotal();
        List<CourseBase> records = courseBaseIPage.getRecords();

        PageResult<CourseBase> pageResult = new PageResult<>(records,current,pageParams.getPageNo(), pageParams.getPageSize());
        System.out.println(pageResult);

//        courseBaseMapper.selectPage()

    }

}
