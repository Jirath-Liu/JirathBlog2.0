package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogTagDao;
import com.jirath.jirathblog2.dao.TagDao;
import com.jirath.jirathblog2.pojo.BlogTag;
import com.jirath.jirathblog2.pojo.Tag;
import com.jirath.jirathblog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jirath
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;
    @Autowired
    BlogTagDao blogTagDao;
    @Override
    public void addTag(Tag tag) {
        tagDao.newTag(tag);
    }

    @Override
    public void addPsgToTag(BlogTag blogTag) {
        blogTagDao.addMsg(blogTag);
    }

    @Override
    public void delTag(int tagId) {
        blogTagDao.delByTag(tagId);
        tagDao.delTag(tagId);
    }

    @Override
    public void fixTag(Tag tag) {
        tagDao.fixTag(tag);
    }

    @Override
    public void delBlogInTag(BlogTag blogTag) {
        blogTagDao.delSpecificRecord(blogTag);
    }

    @Override
    public Object getAll() {
        return tagDao.allTag();
    }

    @Override
    public Object getByBlogId(Integer blogId) {
        List<Integer> ids= blogTagDao.getByBlogId(blogId);
        if (blogId!=null) {
            return tagDao.selectListByIds(ids);
        }
        return null;
    }
}
