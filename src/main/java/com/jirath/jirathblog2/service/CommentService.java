package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.Comment;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Jirath
 */
public interface CommentService {

    /**
     * 获取博客的评论，不分页
     * @param blogId
     * @return
     */
    List<Comment> getPsgComment(@NotNull Integer blogId);
    /**
     * 评论文章，对象中含有评论内容，文章号
     * 用户Id，评论次序，从后端获得，
     * @param comment
     */
    void comment(Comment comment);

    /**
     * 删评论
     * @param commentId 评论号
     */
    void delComment(int commentId,String mail);

    void delCommentList(List<Integer> ids);
}