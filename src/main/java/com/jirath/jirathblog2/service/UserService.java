package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.exception.ExistedAccountException;
import com.jirath.jirathblog2.pojo.User;

/**
 * @author Jirath
 */
public interface UserService {
    /**
     * 用account账户名获得完整的信息
     * @param account 账户名
     * @return 数据库中完整的信息，包含密码用户名等等等等
     */
    User getInfoByAccount(String account);

    /**
     * 用id获取用户的身份
     * @param id UserId
     * @return 身份码
     */
    int getIdentityById(int id);

    /**
     * 添加新用户,其中密码需要使用工具类加密，若用户存在，则抛出ExistedAccountException异常
     * @param user 用户信息
     *
     */
    void insertUser(User user) throws ExistedAccountException;
}
