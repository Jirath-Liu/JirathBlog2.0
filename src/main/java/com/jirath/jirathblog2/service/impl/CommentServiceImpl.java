package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.dao.CommentDao;
import com.jirath.jirathblog2.pojo.Comment;
import com.jirath.jirathblog2.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Jirath
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> getPsgComment(@NotNull Integer blogId) {
        return commentDao.selectBlogCommentList(blogId);
    }

    /**
     * 需要查用户Id,当前评论数目,
     * @param comment
     */
    @Override
    public void comment(Comment comment) {
        Assert.notNull(comment.getBlogId(),"id不能为空");
        Assert.notNull(comment.getCommentMail(),"邮箱不能为空");
        Assert.notNull(comment.getCommentName(),"姓名不能为空");
        blogDao.fixCommentNum(comment.getBlogId());
        comment.setCommentOrder(blogDao.getCommentNum(comment.getBlogId()));
        commentDao.addComment(comment);
    }

    @Override
    public void delComment(int commentId) {
        commentDao.deleteByComId(commentId);
    }

    @Override
    public Boolean commentCanDel(int commentId) {
        return false;
    }
}
