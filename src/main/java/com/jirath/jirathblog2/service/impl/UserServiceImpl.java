package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.exception.ExistedAccountException;
import com.jirath.jirathblog2.dao.UserDao;
import com.jirath.jirathblog2.dao.UserIdentityDao;
import com.jirath.jirathblog2.pojo.User;
import com.jirath.jirathblog2.pojo.UserIdentity;
import com.jirath.jirathblog2.service.UserService;
import com.jirath.jirathblog2.util.ShiroEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jirath
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserIdentityDao userIdentityDao;
    @Autowired
    ShiroEncryptUtil shiroEncryptUtil;

    /**
     * 用账号拿数据库的信息
     * @param account 账号
     * @return User对象，包含所有信息
     */
    @Override
    public User getInfoByAccount(String account){
        return userDao.getUserMsgByAccount(account);
    }

    @Override
    public int getIdentityById(int id) {
        return userIdentityDao.getIdentityById(id);
    }

    @Override
    public void insertUser(User user) throws ExistedAccountException {
        //加密操作
        user.setUserPassword(shiroEncryptUtil.encrypt(user.getUserPassword()));

        if(userDao.getUserMsgByAccount(user.getUserAccount())!=null){
            throw new ExistedAccountException("账号"+user.getUserAccount()+"已经存在");
        }else {
            userDao.insertUser(user);
            //执行插入身份操作时，若已经存在是无影响的
            userIdentityDao.insertUserIdentity(new UserIdentity(user.getUserId(),0));
        }
    }
}
