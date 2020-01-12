package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.dao.CommentDao;
import com.jirath.jirathblog2.pojo.Comment;
import com.jirath.jirathblog2.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jirath
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    CommentDao commentDao;
    /**
     * 需要查用户Id,当前评论数目,
     * @param comment
     */
    @Override
    public void comment(Comment comment) {
        Subject subject;
        try {
            subject= SecurityUtils.getSubject();
            //取出签名并转换为整数类型
            comment.setCommentAuthorId(Integer.valueOf(subject.getPreviousPrincipals().getPrimaryPrincipal().toString()));
            blogDao.fixCommentNum(comment.getBlogId());
            comment.setCommentOrder(blogDao.getCommentNum(comment.getBlogId()));
            commentDao.addComment(comment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delComment(int commentId) {
        commentDao.deleteByComId(commentId);
    }
}
