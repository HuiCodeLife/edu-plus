package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.vo.TeachplanVo;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author Lin
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    @Autowired
    private TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanVo> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }
}
