package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.LeaveMsgDao;
import com.jirath.jirathblog2.pojo.LeaveMsg;
import com.jirath.jirathblog2.service.LeaveMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jirath
 * @date 2020/6/22
 * @description:
 */
@Service
public class LeaveMsgServiceImpl implements LeaveMsgService {
    @Autowired
    LeaveMsgDao leaveMsgDao;

    @CacheEvict(cacheNames = "leaveMsg",allEntries = true)
    @Override
    public void addMsg(LeaveMsg msg) {
        leaveMsgDao.addMsg(msg);
    }

    @CacheEvict(cacheNames = "leaveMsg",allEntries = true)
    @Override
    public void deleMsg(int id) {
        leaveMsgDao.deleMsg(id);
    }

    @CacheEvict(cacheNames = "leaveMsg",allEntries = true)
    @Override
    public void deleMsgList(List<Integer> ids) {
        leaveMsgDao.deleMsgList(ids);
    }

    @Cacheable(cacheNames="leaveMsg",key = "#root.methodName")
    @Override
    public List<LeaveMsg> getAll() {
        return leaveMsgDao.getAll();
    }
}
