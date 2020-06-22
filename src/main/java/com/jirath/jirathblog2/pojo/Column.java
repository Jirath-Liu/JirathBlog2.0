package com.jirath.jirathblog2.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jirath
 */
@Data
public class Column implements Serializable {

  private Integer columnId;
  private String columnPresentation;

  public Column(int columnId, String columnPresentation) {
    this.columnId = columnId;
    this.columnPresentation = columnPresentation;
  }

  private static final long serialVersionUID = -8742448824652078965L;
}
