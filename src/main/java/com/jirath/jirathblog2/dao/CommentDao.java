package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface CommentDao {
    /**
     * 查博客评论
     * @param blogId
     * @return
     */
    List<Comment> selectBlogCommentList(Integer blogId);
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

    @Select("SELECT comment_mail FROM comment WHERE comment_id ={value}")
    String selectMailById(int commentId);

    void deleteBycomIdList(List<Integer> comments);

    void deleteByBlogIdList(List<Integer> ids);
}
