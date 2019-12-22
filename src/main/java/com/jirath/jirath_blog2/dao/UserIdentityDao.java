package com.jirath.jirath_blog2.dao;

import com.jirath.jirath_blog2.pojo.UserIdentity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIdentityDao {
    List<UserIdentity> getAll();
}
