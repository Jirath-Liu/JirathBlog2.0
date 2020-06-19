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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Jirath
 */
@Service
public class BlogContentServiceImpl implements BlogContentService {
    Logger logger= LoggerFactory.getLogger(this.getClass());
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
        blogDao.fixVisitNum(id);
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

    /**
     * @param blogId
     */
    @Override
    public void delete(int blogId) {
        commentDao.deleteByBlogId(blogId);
        blogTagDao.deleteByBlogId(blogId);
        blogColumnDao.deleteByBlogId(blogId);
        blogDao.deleteBlogById(blogId);
    }

    @Override
    public void delete(List<Integer> ids){
        commentDao.deleteByBlogIdList(ids);
        blogTagDao.deleteByBlogIdList(ids);
        blogColumnDao.deleteByBlogIdList(ids);
        blogDao.deleteBlogByIdList(ids);
    }

    @Override
    public void fix(Blog blog) {
        blogDao.fixBlog(blog);
    }

    @Override
    public void fixAT(Integer id, String title, String author) {
        Assert.notNull(id,"文章id不为空");
        Blog blog=new Blog();
        blog.setBlogAuthor(author);
        blog.setBlogId(id);
        blog.setBlogTitle(title);
        logger.info("修改文章："+blog);
        blogDao.fixBlogAT(blog);
    }
}
