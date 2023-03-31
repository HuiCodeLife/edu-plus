package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
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
    List<TeachplanVo> findTeachplanTree(long courseId);


    /**
     * 添加课程计划
     * @param teachplanDto 课程计划相关参数
     */
    void saveTeachplan(SaveTeachplanDto teachplanDto);

    /**
     * 删除课程计划节点
     * @param teachplanId 课程计划id
     */
    void delTeachplanById(Long teachplanId);

}
