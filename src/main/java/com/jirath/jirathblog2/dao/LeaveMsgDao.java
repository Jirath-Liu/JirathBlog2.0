package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.LeaveMsg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface LeaveMsgDao {
    /**
     * @param msg 内容
     * 增加信息
     */
    void addMsg(LeaveMsg msg);

    /**
     * @param id 留言号
     * 删除留言
     */
    void deleMsg(int id);

    /**
     * 获取留言
     * @return 所有留言信息
     */
    List<LeaveMsg> getAll();
}
