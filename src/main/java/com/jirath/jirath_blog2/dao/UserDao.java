package com.jirath.jirath_blog2.dao;

import com.jirath.jirath_blog2.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    /**
     * 获取账户信息
     * @param account
     * @return
     */
    User getUserMsgByAccount(String account);

    /**
     * 查入新用户记录
     * @param user pojo对象
     * @return 已存在则返回0成功为1
     */
    int insertUser(User user);
}
