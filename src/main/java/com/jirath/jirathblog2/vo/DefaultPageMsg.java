package com.jirath.jirathblog2.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jirath
 */
@Data
@Builder
public class DefaultPageMsg implements Serializable {
    private List<BlogVo> blogList;
    private int blogNum;
    private int pageNum;

    private static final long serialVersionUID = -8742448824652078965L;
}
