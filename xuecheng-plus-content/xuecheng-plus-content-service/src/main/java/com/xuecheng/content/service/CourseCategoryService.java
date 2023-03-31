package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.vo.CourseCategoryTreeVo;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author Lin
 * @since 2023-03-29
 */
public interface CourseCategoryService extends IService<CourseCategory> {


    /**
     * 课程分类树形结构查询
     * @param id 分类id
     * @return 结果
     */
    public List<CourseCategoryTreeVo> queryTreeNodes(String id);
}
