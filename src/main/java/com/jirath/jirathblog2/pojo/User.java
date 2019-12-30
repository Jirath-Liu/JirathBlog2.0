package com.jirath.jirathblog2.pojo;

import lombok.Data;

/**
 * @author Jirath
 */
@Data
public class User {

  private int userId;
  private String userName;
  private String userAccount;
  private String userPassword;
  private String userMail;

  public String getBasicMsg(){
    return "userId="+userId+
            "userAccount="+userAccount;
  }
}
