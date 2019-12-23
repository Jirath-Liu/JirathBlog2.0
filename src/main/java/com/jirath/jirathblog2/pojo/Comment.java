package com.jirath.jirathblog2.pojo;

import lombok.Data;

@Data
public class Comment {

  private int commentId;
  private int blogId;
  private int commentOrder;
  private String commentContent;
  private int commentAuthorId;
  private java.util.Date commentTime;

}
