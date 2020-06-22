package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.dao.BlogTagDao;
import com.jirath.jirathblog2.dao.CommentDao;
import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.pojo.Tag;
import com.jirath.jirathblog2.query.PageScope;
import com.jirath.jirathblog2.service.BlogContentService;
import com.jirath.jirathblog2.service.TagService;
import com.jirath.jirathblog2.util.SystemSettingUtil;
import com.jirath.jirathblog2.vo.BlogVo;
import com.jirath.jirathblog2.vo.DefaultPageMsg;
import com.jirath.jirathblog2.vo.PageMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 该类下查询使用了Redis缓存，所有修改操作都将清空缓存库
 * @author Jirath
 */
@Service
public class BlogContentServiceImpl implements BlogContentService {
    private boolean flag=false;
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    BlogDao blogDao;
    @Autowired
    BlogTagDao blogTagDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    TagService tagService;
    @Value("${blog-num-every-page}")
    int pageBlogNum;

    @Override
    public BlogVo getPsgById(int id) {
        blogDao.fixVisitNum(id);
        BlogVo blogVo= blogDao.getMsgById(id);
        List<Tag> tags=tagService.getByBlogId(id);
        if (tags!=null) blogVo.setTags(tags);
        return blogVo;
    }

    /**
     * 包含了多表联查，可以在这里考虑进一步优化
     * @return
     */
    @Cacheable(cacheNames = "blog",key = "#root.methodName")
    @Override
    public DefaultPageMsg getDefaultPageContent() {
        List<BlogVo> blogList=getBlogVoList(new PageScope(0,pageBlogNum));
        int blogNum=blogDao.getBlogNum();
        int pageNum = (blogNum % pageBlogNum == 0) ? (blogNum / pageBlogNum) : (blogNum / pageBlogNum + 1);
        return DefaultPageMsg.builder()
                .blogList(blogList)
                .blogNum(blogNum)
                .pageNum(pageNum)
                .build();
    }

    @Cacheable(cacheNames = "blog",key = "#root.methodName+#page")
    @Override
    public PageMsg getSpecificPage(int page) {
        int blogNum=blogDao.getBlogNum();
        int pageNum = (blogNum % pageBlogNum == 0) ? (blogNum / pageBlogNum) : (blogNum / pageBlogNum + 1);
        List<BlogVo>blogList= getBlogVoList(new PageScope((page-1)*pageBlogNum,pageBlogNum));
        return PageMsg.builder()
                .blogList(blogList)
                .onPage(page)
                .pageNum(pageNum)
                .build();
    }
    private List<BlogVo> getBlogVoList(PageScope pageScope){
        Assert.notNull(pageScope,"分页信息不能为null");
        List<BlogVo>blogList= blogDao.getSpecificPage(pageScope);
        tagService.setTagListToBlogVoList(blogList);
        return blogList;
    }


    /**
     * 考虑给默认分类可以修改
     * @param blog 文章信息
     * @return
     */
    @CacheEvict(cacheNames = "blog",allEntries = true)
    @Override
    public Blog addPassage(Blog blog) {
        if (blog.getBlogColumnId()==null) blog.setBlogColumnId(1);
        blogDao.addPassage(blog);
        return blog;
    }

    @Override
    public BlogVo getLatest() {
        BlogVo blogVo=blogDao.getLatest();
        blogVo.setTags(tagService.getByBlogId(blogVo.getBlogId()));
        return blogVo;
    }

    @Override
    public BlogVo getRand() {
        BlogVo blogVo=blogDao.getRandPsg();
        blogVo.setTags(tagService.getByBlogId(blogVo.getBlogId()));
        return blogVo;
    }


    /**
     * @param blogId
     */
    @CacheEvict(cacheNames = "blog",allEntries = true)
    @Override
    public void delete(int blogId) {
        commentDao.deleteByBlogId(blogId);
        blogTagDao.deleteByBlogId(blogId);
        blogDao.deleteBlogById(blogId);
    }

    @CacheEvict(cacheNames = "blog",allEntries = true)
    @Override
    public void delete(List<Integer> ids){
        commentDao.deleteByBlogIdList(ids);
        blogTagDao.deleteByBlogIdList(ids);
        blogDao.deleteBlogByIdList(ids);
    }

    @CacheEvict(cacheNames = "blog",allEntries = true)
    @Override
    public void fix(Blog blog) {
        blogDao.fixBlog(blog);
    }

    @CacheEvict(cacheNames = "blog",allEntries = true)
    @Override
    public void fixATC(Integer id, String title, String author,Integer column) {
        Assert.notNull(id,"文章id不为空");
        if (column==null) column= SystemSettingUtil.getDefaultColumn();
        Blog blog=new Blog();
        blog.setBlogColumnId(column);
        blog.setBlogAuthor(author);
        blog.setBlogId(id);
        blog.setBlogTitle(title);
        logger.info("修改文章："+blog);
        blogDao.fixBlogATC(blog);
    }
}
