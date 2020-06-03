package com.jirath.jirathblog2.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jirath
 */
@Data
public class Comment {

  private int commentId;
  private int blogId;
  private int commentOrder;
  private String commentContent;
  private String commentMail;
  private String commentName;
  private LocalDateTime commentTime;

}
