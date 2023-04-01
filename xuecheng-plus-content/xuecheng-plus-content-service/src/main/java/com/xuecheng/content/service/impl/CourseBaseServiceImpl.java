package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.model.vo.CourseBaseInfoVo;
import com.xuecheng.content.service.CourseBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author Lin
 */
@Slf4j
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements CourseBaseService {


    @Autowired
    private CourseBaseMapper courseBaseMapper;


    @Autowired
    private CourseMarketMapper courseMarketMapper;

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> getList(PageParams pageParams, QueryCourseParamsDto queryCourseParams) {
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        // 课程名称模糊查询条件
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParams.getCourseName()),
                CourseBase::getName,
                queryCourseParams.getCourseName());

        // 审核状态查询条件
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParams.getAuditStatus()),
                CourseBase::getAuditStatus,
                queryCourseParams.getAuditStatus());

        // 发布状态查询条件
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParams.getPublishStatus()),
                CourseBase::getStatus,
                queryCourseParams.getPublishStatus());
        IPage<CourseBase> page = courseBaseMapper.selectPage(new Page<>(pageParams.getPageNo(), pageParams.getPageSize()), queryWrapper);
        return new PageResult<>(page.getRecords(), page.getTotal(), pageParams.getPageNo(), pageParams.getPageSize());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CourseBaseInfoVo createCourseBase(Long companyId, AddCourseDto dto) {
        //新增对象
        CourseBase courseBaseNew = new CourseBase();
        //将填写的课程信息赋值给新增对象
        BeanUtils.copyProperties(dto, courseBaseNew);
        //设置审核状态
        courseBaseNew.setAuditStatus("202002");
        //设置发布状态
        courseBaseNew.setStatus("203001");
        //机构id
        courseBaseNew.setCompanyId(companyId);

        //添加时间
        courseBaseNew.setCreateDate(LocalDateTime.now());
        //插入课程基本信息表
        int insert = courseBaseMapper.insert(courseBaseNew);
        if (insert <= 0) {
            throw new RuntimeException("新增课程基本信息失败");
        }
        CourseMarket courseMarketNew = new CourseMarket();
        Long courseId = courseBaseNew.getId();
        BeanUtils.copyProperties(dto,courseMarketNew);
        courseMarketNew.setId(courseId);
        int i = saveCourseMarket(courseMarketNew);
        if(i<=0){
            throw new RuntimeException("保存课程营销信息失败");
        }
        //查询课程基本信息及营销信息并返回
        return getCourseBaseInfo(courseId);
    }

    /**
     * 返回结果
     * @param courseId 课程id
     * @return 结果
     */
    public CourseBaseInfoVo getCourseBaseInfo(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase == null){
            return null;
        }
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoVo courseBaseInfoVo = new CourseBaseInfoVo();
        // 复制courseBase属性
        BeanUtils.copyProperties(courseBase,courseBaseInfoVo);
        // 复制courseMarket属性
        if(courseMarket != null){
            BeanUtils.copyProperties(courseMarket,courseBaseInfoVo);
        }
        // 查询分类名称
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoVo.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoVo.setMtName(courseCategoryByMt.getName());
        return courseBaseInfoVo;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CourseBaseInfoVo updateCourseBase(Long companyId, EditCourseDto dto) {

        //课程id
        Long courseId = dto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase==null){
            XueChengPlusException.cast("课程不存在");
        }

        //校验本机构只能修改本机构的课程
        if(!courseBase.getCompanyId().equals(companyId)){
            XueChengPlusException.cast("本机构只能修改本机构的课程");
        }

        //封装基本信息的数据
        BeanUtils.copyProperties(dto,courseBase);
        courseBase.setChangeDate(LocalDateTime.now());

        //更新课程基本信息
        int i = courseBaseMapper.updateById(courseBase);
        if (i <= 0) {
            XueChengPlusException.cast("课程更新失败");
        }
        //封装营销信息的数据
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto,courseMarket);
        saveCourseMarket(courseMarket);
        //查询课程信息
        CourseBaseInfoVo courseBaseInfo = this.getCourseBaseInfo(courseId);
        return courseBaseInfo;
    }

    @Override
    public void deleteCouserBaseInfo(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if ("203002".equals(courseBase.getStatus())) {
            XueChengPlusException.cast("发布状态的课程不允许删除");
        }
        courseBaseMapper.deleteById(courseBase.getId());
    }

    /**
     * 保存课程销售信息，原先没有则新增 ，有则更新
     * @param courseMarketNew 课程销售数据
     * @return 结果
     */
    private int saveCourseMarket(CourseMarket courseMarketNew) {
        //收费规则
        String charge = courseMarketNew.getCharge();
        //收费规则为收费
        if("201001".equals(charge)){
            if(courseMarketNew.getPrice() == null || courseMarketNew.getPrice() <=0){
                throw new RuntimeException("课程为收费价格不能为空且必须大于0");
            }
        }
        //根据id从课程营销表查询
        CourseMarket courseMarketObj = courseMarketMapper.selectById(courseMarketNew.getId());
        if(courseMarketObj == null){
            // 第一次添加
            return courseMarketMapper.insert(courseMarketNew);
        }else{
            // 更新
            BeanUtils.copyProperties(courseMarketNew,courseMarketObj);
            return courseMarketMapper.updateById(courseMarketObj);
        }

    }
}
