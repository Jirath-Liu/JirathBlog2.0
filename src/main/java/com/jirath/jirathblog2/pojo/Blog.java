package com.jirath.jirathblog2.pojo;

import lombok.Data;

/**
 * @author Jirath
 */
@Data
public class Blog {

  private Integer blogId;
  private String blogContent;
  private String blogTitle;
  private String blogAuthor;
  private Integer blogColumnId;
  private Integer blogCommentQuantity;
  private java.util.Date blogCreateTime;
  private java.util.Date blogLastFixTime;
  private Integer visitedTime;
}
