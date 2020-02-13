package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author Jirath
 */
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
    Integer insertUser(User user);

}
