package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.query.PageScope;
import com.jirath.jirathblog2.service.BlogContentService;
import com.jirath.jirathblog2.vo.DefaultPageMsg;
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
    @Value("${blog-num-every-page}")
    int pageBlogNum;
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
    public List<Blog> getSpecificPage(int page) {
        return blogDao.getSpecificPage(new PageScope((page-1)*pageBlogNum,pageBlogNum));
    }

    @Override
    public void addPassage(Blog blog) {

        blogDao.addPassage(blog);
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
        blogDao.deleteBlogById(blogId);
    }
}
