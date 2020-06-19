package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.dao.CommentDao;
import com.jirath.jirathblog2.pojo.Comment;
import com.jirath.jirathblog2.service.CommentService;
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
        //commentId与实际评论数量相差1，不需要再加一
        comment.setCommentOrder(blogDao.getCommentNum(comment.getBlogId()));
        commentDao.addComment(comment);
    }

    @Override
    public void delComment(int commentId,String mail) {
        String ma=commentDao.selectMailById(commentId);
        Assert.notNull(ma,"不存在该评论");
        Assert.isTrue(!mail.equals(ma),"评论邮箱错误");
        commentDao.deleteByComId(commentId);
    }

    /**
     * 需要核查
     * @param comments
     */
    @Override
    public void delCommentList(List<Integer> comments){
        commentDao.deleteBycomIdList(comments);
    }

}
