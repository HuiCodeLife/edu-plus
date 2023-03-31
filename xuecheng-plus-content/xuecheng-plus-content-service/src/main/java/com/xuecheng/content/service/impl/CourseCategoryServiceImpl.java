package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.vo.CourseCategoryTreeVo;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author Lin
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeVo> queryTreeNodes(String id) {
        // 递归查询所有分类列表
        List<CourseCategoryTreeVo> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);

        //将list转map,以备使用,排除根节点
        Map<String, CourseCategoryTreeVo> mapTemp = courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).collect(Collectors.toMap(CourseCategory::getId, value -> value, (key1, key2) -> key2));

        //最终返回的list
        List<CourseCategoryTreeVo> categoryTreeDtos = new ArrayList<>();
        //依次遍历每个元素,排除根节点
        courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).forEach(item -> {
            // 一级菜单
            if (item.getParentid().equals(id)) {
                categoryTreeDtos.add(item);
            }
            //找到当前节点的父节点
            CourseCategoryTreeVo courseCategoryTreeDto = mapTemp.get(item.getParentid());
            if (courseCategoryTreeDto != null) {
                if (courseCategoryTreeDto.getChildrenTreeNodes() == null) {
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeVo>());
                }
                //下边开始往ChildrenTreeNodes属性中放子节点
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
        });
        return categoryTreeDtos;
    }


}
