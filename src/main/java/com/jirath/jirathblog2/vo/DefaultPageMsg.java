package com.jirath.jirathblog2.vo;

import com.jirath.jirathblog2.pojo.Blog;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Jirath
 */
@Data
@Builder
public class DefaultPageMsg {
    private List<Blog> blogList;
    private int blogNum;
    private int pageNum;
}
