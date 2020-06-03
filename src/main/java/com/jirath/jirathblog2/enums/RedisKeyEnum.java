package com.jirath.jirathblog2.enums;

/**
 * @author Jirath
 * @date 2020/5/8
 * @description:
 */
public enum RedisKeyEnum {
    visitNum("blog_visit_times"),
    startTime("blog_start_time");

    private String key;
    RedisKeyEnum(String key) {
        this.key=key;
    }
    public String getKey(){
        return key;
    }
}
