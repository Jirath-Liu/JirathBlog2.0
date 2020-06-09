package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.Friend;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 * @date 2020/6/9
 * @description:
 */
@Repository
public interface FriendDao {
    @Select("SELECT * FROM friend")
    List<Friend> getAll();
}
