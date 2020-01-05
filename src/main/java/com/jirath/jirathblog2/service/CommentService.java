package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.Comment;

/**
 * @author Jirath
 */
public interface CommentService {
    /**
     * 评论文章，对象中含有评论内容，文章号
     * 用户Id，评论次序，从后端获得，
     * @param comment
     */
    void comment(Comment comment);
}
