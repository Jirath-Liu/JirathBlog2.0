package com.jirath.jirathblog2.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Jirath
 */
@Data
public class UserIdentity {

  private int userId;
  private int userIdentity;

  public UserIdentity(int userId, int userIdentity) {
    this.userId = userId;
    this.userIdentity = userIdentity;
  }
}
