package com.jirath.jirathblog2.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jirath
 */
@Data
public class Tag implements Serializable {

  private Integer tagId;
  private String tagName;

  private static final long serialVersionUID = -8742448824652078965L;
}
