package com.jirath.jirathblog2.pojo;

import lombok.Data;

/**
 * @author Jirath
 */
@Data
public class BlogColumn {

  private int blogId;
  private int columnId;

  public BlogColumn(int blogId, int columnId) {
    this.blogId = blogId;
    this.columnId = columnId;
  }
}
