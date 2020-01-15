package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogColumnDao;
import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.dao.BlogTagDao;
import com.jirath.jirathblog2.dao.CommentDao;
import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.query.PageScope;
import com.jirath.jirathblog2.service.BlogContentService;
import com.jirath.jirathblog2.vo.DefaultPageMsg;
import com.jirath.jirathblog2.vo.PageMsg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Jirath
 */
@Service
public class BlogContentServiceImpl implements BlogContentService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    BlogColumnDao blogColumnDao;
    @Autowired
    BlogTagDao blogTagDao;
    @Autowired
    CommentDao commentDao;
    @Value("${blog-num-every-page}")
    int pageBlogNum;

    @Override
    public Blog getPsgById(int id) {
        return blogDao.getMsgById(id);
    }

    @Override
    public DefaultPageMsg getDefaultPageContent() {
        List<Blog> blogList=blogDao.getSpecificPage(new PageScope(0,pageBlogNum));
        int blogNum=blogDao.getBlogNum();
        int pageNum = (blogNum % pageBlogNum == 0) ? (blogNum / pageBlogNum) : (blogNum / pageBlogNum + 1);
        return DefaultPageMsg.builder()
                .blogList(blogList)
                .blogNum(blogNum)
                .pageNum(pageNum)
                .build();
    }

    @Override
    public PageMsg getSpecificPage(int page) {
        int blogNum=blogDao.getBlogNum();
        int pageNum = (blogNum % pageBlogNum == 0) ? (blogNum / pageBlogNum) : (blogNum / pageBlogNum + 1);
        return PageMsg.builder()
                .blogList(blogDao.getSpecificPage(new PageScope((page-1)*pageBlogNum,pageBlogNum)))
                .onPage(page)
                .pageNum(pageNum)
                .build();
    }

    @Override
    public Blog addPassage(Blog blog) {
        blogDao.addPassage(blog);
        return blog;
    }

    @Override
    public Blog getLatest() {
        return blogDao.getLatest();
    }

    @Override
    public Blog getRand() {
        return blogDao.getRandPsg();
    }

    @Override
    public void delete(int blogId) {
        commentDao.deleteByBlogId(blogId);
        blogTagDao.deleteByBlogId(blogId);
        blogColumnDao.deleteByBlogId(blogId);
        blogDao.deleteBlogById(blogId);
    }

    @Override
    public void fix(Blog blog) {

    }
}
