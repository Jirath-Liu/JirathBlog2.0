package com.jirath.jirathblog2.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Jirath
 */
@Repository
public interface BlogColumnDao {
    /**
     * 根据文章Id删除记录
     * @param blogId
     */
    void deleteByBlogId(int blogId);
}
