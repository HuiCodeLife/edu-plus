package com.xuecheng.content.model.vo;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Lin
 * @since: 2023-03-30
 */
@Data
public class CourseCategoryTreeVo extends CourseCategory implements Serializable {

    /**
     * 子节点数据列表
     */
    List<CourseCategoryTreeVo> childrenTreeNodes;
}
