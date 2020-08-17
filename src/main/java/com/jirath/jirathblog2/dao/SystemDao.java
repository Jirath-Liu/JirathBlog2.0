package com.jirath.jirathblog2.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author Jirath
 * @date 2020/5/7
 * @description:
 */
@Repository
public interface SystemDao {
    @Select("SELECT visit_times FROM `system`")
    Long getVisitTimes();
    @Select("SELECT start_time FROM `system`")
    LocalDateTime getStartTime();
    @Update("UPDATE `system` SET visit_times=#{value}")
    void updateVisitTimes(Long times);
    @Update("UPDATE `system` SET start_time=#{value}")
    void updateStartTimes(LocalDateTime time);
}
