package com.jirath.jirathblog2.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jirath
 */
@Data
public class LeaveMsg implements Serializable {
    private Integer leaveMsgId;
    private String leaveMsg;
    private String mail;
    private String name;

    private static final long serialVersionUID = -8742448824652078965L;
}
