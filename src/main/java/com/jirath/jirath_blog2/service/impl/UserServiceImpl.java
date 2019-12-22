package com.jirath.jirath_blog2.service.impl;

import com.jirath.jirath_blog2.dao.UserDao;
import com.jirath.jirath_blog2.pojo.User;
import com.jirath.jirath_blog2.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSerivce {
    @Autowired
    UserDao userDao;

    /**
     * 用账号拿数据库的信息
     * @param account 账号
     * @return User对象，包含所有信息
     */
    @Override
    public User getInfoByAccount(String account){
        return userDao.getUserMsgByAccount(account);
    }
}
