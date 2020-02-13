package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.UserIdentity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface UserIdentityDao {
    /**
     * 获得所有信息
     * @return 所有用户身份信息
     */
    List<UserIdentity> getAll();

    /**
     * 获得userId为id的用户的身份
     * @param id userId
     * @return 身份码
     */
    Integer getIdentityById(int id);

    /**
     * 增加用户身份，
     * @param userIdentity 用户身份对象
     * @return 已存在返回0
     */
    Integer insertUserIdentity(UserIdentity userIdentity);
}
