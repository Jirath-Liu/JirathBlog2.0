package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.dao.ColumnDao;
import com.jirath.jirathblog2.pojo.Column;
import com.jirath.jirathblog2.pojo.Tag;
import com.jirath.jirathblog2.query.FixColumn;
import com.jirath.jirathblog2.service.ColumnService;
import com.jirath.jirathblog2.service.TagService;
import com.jirath.jirathblog2.util.SystemSettingUtil;
import com.jirath.jirathblog2.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jirath
 */
@Service
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    ColumnDao columnDao;
    @Autowired
    TagService tagService;

    @CacheEvict(cacheNames = "column",allEntries = true)
    @Override
    public void addColumn(String description) {
        columnDao.addColumn(description);
    }

    @Cacheable(cacheNames = "column",key = "#root.methodName")
    @Override
    public List<Column> getAllColumn() {
        return columnDao.getAll();
    }

    @CacheEvict(cacheNames = "column",allEntries = true)
    @Override
    public void fixColumnMsg(int id,String name) {
        Column column=new Column(id,name);
        columnDao.fixMsg(column);
    }

    @Override
    public List<BlogVo> getColumnPsg(int columnId) {
        List<BlogVo> blogList=blogDao.getMsgByColumnId(columnId);
        tagService.setTagListToBlogVoList(blogList);
        return blogList;
    }

    /**
     *
     * //若为默认分类，必须保证先删除tag
     * 若不是默认分类，修改为默认分类
     * @param columnId
     */
    @CacheEvict(cacheNames = "column",allEntries = true)
    @Override
    public void deleteColumnById(int columnId) {
        SystemSettingUtil.getDefaultColumn();
       // blogDao.deleteByColumn(columnId);
        blogDao.fixBlogColumn(new FixColumn(columnId,SystemSettingUtil.getDefaultColumn()));
        columnDao.deleteColumn(columnId);

    }

}
