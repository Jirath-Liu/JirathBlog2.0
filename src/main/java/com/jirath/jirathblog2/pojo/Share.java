package com.jirath.jirathblog2.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jirath
 * @date 2020/6/30
 * @description:
 */
@Data
public class Share implements Serializable {
    private Integer id;
    private String name;
    private String url;
    private static final long serialVersionUID = -8742448824652078965L;

}
