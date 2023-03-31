package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.vo.CourseCategoryTreeVo;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author Lin
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {


    /**
     * 查询课程分类
     * @param id 分类id
     * @return 结果
     */
    public List<CourseCategoryTreeVo> selectTreeNodes(String id);

}
