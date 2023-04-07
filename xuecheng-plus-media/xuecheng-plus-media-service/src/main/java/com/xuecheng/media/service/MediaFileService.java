package com.xuecheng.media.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.model.vo.UploadFileResultVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * @author Lin
 */
public interface MediaFileService {


    /**
     * 查询media信息
     * @param companyId
     * @param pageParams
     * @param queryMediaParamsDto
     * @return
     */
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);


    /**
     * 上传文件
     * @param companyId 机构id
     * @param uploadFileParamsDto 上传参数信息
     * @param localFilePath 本地文件路径
     * @return 结果
     */
    public UploadFileResultVo uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath);


    /**
     * 添加到数据库
     * @param companyId 机构id
     * @param fileMd5 文件md5
     * @param uploadFileParamsDto  上传参数信息
     * @param bucket 桶
     * @param objectName 文件名
     * @return 结果
     */
    public MediaFiles addMediaFilesToDb(Long companyId, String fileMd5, UploadFileParamsDto uploadFileParamsDto, String bucket, String objectName);
}
