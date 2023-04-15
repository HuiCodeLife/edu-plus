package com.xuecheng.learning.feignclient;

import com.xuecheng.content.model.po.CoursePublish;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 查询课程发布信息降级处理
 * @author Lin
 */
@Slf4j
@Component
public class ContentServiceClientFallbackFactory implements FallbackFactory<ContentServiceClient> {
    @Override
    public ContentServiceClient create(Throwable throwable) {
        return courseId -> {
            log.error("调用内容管理服务发生熔断:{}", throwable.toString(),throwable);
            return null;
        };
    }
}
