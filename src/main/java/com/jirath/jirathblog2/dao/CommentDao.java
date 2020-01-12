package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author Jirath
 */
@Repository
public interface CommentDao {
    /**
     * 根据文章Id删除记录
     * @param blogId
     */
    void deleteByBlogId(int blogId);

    /**
     * 增加评论记录
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 根据评论号删记录
     * @param commentId
     */
    void deleteByComId(int commentId);
}
