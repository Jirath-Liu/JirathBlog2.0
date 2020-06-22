package com.jirath.jirathblog2.vo;

import com.jirath.jirathblog2.pojo.Blog;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jirath
 */
@Data
@Builder
public class PageMsg implements Serializable {
    private List<BlogVo> blogList;
    private int onPage;
    private int pageNum;

    private static final long serialVersionUID = -8742448824652078965L;
}
