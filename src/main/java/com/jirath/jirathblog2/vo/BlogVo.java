package com.jirath.jirathblog2.vo;

import com.jirath.jirathblog2.pojo.Column;
import com.jirath.jirathblog2.pojo.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jirath
 * @date 2020/6/21
 * @description:
 */
@Data
public class BlogVo implements Serializable {
    private Integer blogId;
    private String blogContent;
    private String blogTitle;
    private String blogAuthor;
    private Integer blogColumnId;
    private Integer blogCommentQuantity;
    private java.util.Date blogCreateTime;
    private java.util.Date blogLastFixTime;
    private Integer visitTime;

    private String columnName;

    private List<Tag> tags;

    private static final long serialVersionUID = -8742448824652078965L;
}
