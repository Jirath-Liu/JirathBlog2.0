package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogTagDao;
import com.jirath.jirathblog2.dao.TagDao;
import com.jirath.jirathblog2.pojo.BlogTag;
import com.jirath.jirathblog2.pojo.Tag;
import com.jirath.jirathblog2.service.TagService;
import com.jirath.jirathblog2.vo.BlogSimpleVo;
import com.jirath.jirathblog2.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 只对高频的所有tag做了缓存
 * @author Jirath
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;
    @Autowired
    BlogTagDao blogTagDao;

    @CacheEvict(cacheNames = "tag" ,allEntries = true)
    @Override
    public void addTag(Tag tag) {
        tagDao.newTag(tag);
    }

    @Override
    public void addPsgToTag(BlogTag blogTag) {
        blogTagDao.addMsg(blogTag);
    }

    @CacheEvict(cacheNames = "tag" ,allEntries = true)
    @Override
    public void delTag(int tagId) {
        blogTagDao.delByTag(tagId);
        tagDao.delTag(tagId);
    }

    @CacheEvict(cacheNames = "tag" ,allEntries = true)
    @Override
    public void fixTag(Tag tag) {
        tagDao.fixTag(tag);
    }

    @Override
    public void delBlogInTag(BlogTag blogTag) {
        blogTagDao.delSpecificRecord(blogTag);
    }

    @Override
    public List<BlogSimpleVo> getPsgByTagId(Integer tagId){
        Assert.notNull(tagId,"tagId不能为null");
        return blogTagDao.getPsgMsgByTagId(tagId);
    }
    @Cacheable(cacheNames = "tag",key = "'allTag'")
    @Override
    public List<Tag> getAll() {
        return tagDao.allTag();
    }

    @Override
    public List<Tag> getByBlogId(Integer blogId) {
        return blogTagDao.getByBlogId(blogId);
    }

    @Override
    public void setTagListToBlogVoList(List<BlogVo> blogList) {
        blogList.stream().forEach(b->{
            List<Tag> tags=this.getByBlogId(b.getBlogId());
            if (tags!=null) b.setTags(tags);
        });
    }

    @Override
    public void delByColumnId(int columnId) {
        blogTagDao.delByColumnId(columnId);
    }
}
