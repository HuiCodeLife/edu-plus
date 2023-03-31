package com.xuecheng.content.model.vo;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;

import java.util.List;

/**
 * @author: Lin
 * @since: 2023-03-31
 */
@Data
public class TeachplanVo extends Teachplan {

    private TeachplanMedia teachplanMedia;

    private List<TeachplanVo> teachPlanTreeNodes;

}
