package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.UserIdentity;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
