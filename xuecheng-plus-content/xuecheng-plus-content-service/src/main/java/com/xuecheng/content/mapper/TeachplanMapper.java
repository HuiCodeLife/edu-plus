package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.vo.TeachplanVo;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author Lin
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {


    /**
     * 查询课程计划
     * @param courseId 课程id
     * @return 结果
     */
    public List<TeachplanVo> selectTreeNodes(long courseId);

}
