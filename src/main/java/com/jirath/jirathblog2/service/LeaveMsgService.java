package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.LeaveMsg;

import java.util.List;

/**
 * @author Jirath
 * @date 2020/6/22
 * @description:
 */
public interface LeaveMsgService {
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

    void deleMsgList(List<Integer> ids);
    /**
     * 获取留言
     * @return 所有留言信息
     */
    List<LeaveMsg> getAll();
}
