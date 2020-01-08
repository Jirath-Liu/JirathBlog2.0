package com.jirath.jirathblog2.pojo;

import lombok.Data;

/**
 * @author Jirath
 */
@Data
public class Column {

  private int columnId;
  private String columnPresentation;

  public Column(int columnId, String columnPresentation) {
    this.columnId = columnId;
    this.columnPresentation = columnPresentation;
  }
}
