package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.vo.TeachplanVo;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author Lin
 * @since 2023-03-29
 */
public interface TeachplanService extends IService<Teachplan> {

    /**
     * 查询课程计划
     * @param courseId 课程id
     * @return 结果
     */
    public List<TeachplanVo> findTeachplanTree(long courseId);
}
