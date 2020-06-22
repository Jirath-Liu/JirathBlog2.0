package com.jirath.jirathblog2.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jirath
 */
@Data
public class Comment {

  private Integer commentId;
  private Integer blogId;
  private Integer commentOrder;
  private String commentContent;
  private String commentMail;
  private String commentName;
  private LocalDateTime commentTime;

}
