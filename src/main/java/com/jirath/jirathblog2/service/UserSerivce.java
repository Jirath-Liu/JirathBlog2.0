package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.User;

public interface UserSerivce {
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
}
