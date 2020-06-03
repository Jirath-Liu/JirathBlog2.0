package com.jirath.jirathblog2.service;

import java.time.LocalDateTime;

/**
 * @author Jirath
 * @date 2020/5/7
 * @description:
 */
public interface SystemService {
    /**
     * 用Spring自带缓存，进行存储
     * @return
     */
    Long getVisitTimes();
    LocalDateTime getStartTime();
}
