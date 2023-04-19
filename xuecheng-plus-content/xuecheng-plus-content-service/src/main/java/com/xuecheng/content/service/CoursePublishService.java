package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.model.vo.CoursePreviewVo;

import java.io.File;

/**
 * <p>
 * 课程发布 服务类
 * </p>
 *
 * @author Lin
 * @since 2023-03-29
 */
public interface CoursePublishService extends IService<CoursePublish> {

    /**
     * 获取课程预览信息
     * @param courseId 课程id
     * @return 结果
     */
    public CoursePreviewVo getCoursePreviewInfo(Long courseId);



    /**
     * 提交审核
     * @param companyId  机构id
     * @param courseId  课程id
     */
    public void commitAudit(Long companyId,Long courseId);

    /**
     * 课程发布接口
     * @param companyId 机构id
     * @param courseId 课程id
     */
    public void publish(Long companyId,Long courseId);

    /**
     * 课程静态化
     * @param courseId  课程id
     * @return File 静态化文件
     */
    public File generateCourseHtml(Long courseId);
    /**
     * 上传课程静态化页面
     * @param file  静态化文件
     */
    public void  uploadCourseHtml(Long courseId, File file);


    /**
     * 获取课程发布信息
     * @param courseId 课程id
     * @return 结果
     */
    CoursePublish getCoursePublish(Long courseId);


    /**
     * 查询缓存中的课程信息
     * @param courseId 课程id
     * @return 结果
     */
    public CoursePublish getCoursePublishCache(Long courseId);

}
