package com.xuecheng.search;

import com.alibaba.fastjson.JSON;
import com.xuecheng.search.dto.SearchCourseParamDto;
import com.xuecheng.search.dto.SearchPageResultDto;
import com.xuecheng.search.po.CourseIndex;
import com.xuecheng.search.service.IndexService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 课程索引与搜索测试
 */
@SpringBootTest
public class CourseIndexSearchTest {


    @Value("${elasticsearch.course.index}")
    private String courseIndexStore;

    @Autowired
    IndexService courseIndexService;

    @Test
    public void test_addindex() {
        CourseIndex courseIndex = new CourseIndex();
        courseIndex.setId(101L);
        courseIndex.setName("Java编程思想");
        courseIndex.setDescription("《Java编程思想》是2007年6月1日机械工业出版社出版的图书，作者是埃克尔，译者是陈昊鹏。主要内容本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作");
        courseIndex.setCompanyId(100000L);
        courseIndex.setCompanyName("北京黑马程序");
        courseIndex.setCharge("10011");
        courseIndex.setMt("1-1");
        courseIndex.setSt("1-1-1");
        courseIndex.setMtName("后端编程");
        courseIndex.setStName("Java语言");
        courseIndex.setGrade("20202");
        courseIndex.setCreateDate(LocalDateTime.now());
        courseIndex.setPic("http://file.xuecheng-plus.com/mediafiles/ssss.jpg");
        courseIndex.setPrice(100f);
        courseIndex.setOriginalPrice(200f);
        courseIndex.setRemark("没有备注");
        courseIndex.setStatus("1");
        courseIndex.setTags("没有标签");
        courseIndex.setValidDays(222);
        courseIndex.setTeachmode("40020");

        Boolean result = courseIndexService.addCourseIndex(courseIndexStore, "101", courseIndex);
        System.out.println(result);

    }

    @Test
    public void test_updateIndex() {

        CourseIndex courseIndex = new CourseIndex();
        courseIndex.setId(101L);
        courseIndex.setName("Java编程思想");
        courseIndex.setDescription("《Java编程思想》是2007年6月1日机械工业出版社出版的图书，作者是埃克尔，译者是陈昊鹏。主要内容本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作");
        courseIndex.setCompanyId(100000L);
        courseIndex.setCompanyName("北京黑马程序");
        courseIndex.setCharge("10011");
        courseIndex.setMt("1-1");
        courseIndex.setSt("1-1-1");
        courseIndex.setMtName("后端编程");
        courseIndex.setStName("Java语言");
        courseIndex.setGrade("20202");
        courseIndex.setCreateDate(LocalDateTime.now());
        courseIndex.setPic("http://file.xuecheng-plus.com/mediafiles/ssss.jpg");
        courseIndex.setPrice(100f);
        courseIndex.setOriginalPrice(200f);
        courseIndex.setRemark("没有备注");
        courseIndex.setStatus("1");
        courseIndex.setTags("没有标签");
        courseIndex.setValidDays(222);
        courseIndex.setTeachmode("40020");

        Boolean result = courseIndexService.updateCourseIndex(courseIndexStore, "101", courseIndex);
        System.out.println(result);

    }

    @Test
    public void test_search(){

    }
}
