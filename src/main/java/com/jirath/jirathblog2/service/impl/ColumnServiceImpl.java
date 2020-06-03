package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.BlogColumnDao;
import com.jirath.jirathblog2.dao.BlogDao;
import com.jirath.jirathblog2.dao.ColumnDao;
import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.pojo.BlogColumn;
import com.jirath.jirathblog2.pojo.Column;
import com.jirath.jirathblog2.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
    BlogColumnDao blogColumnDao;
    @Override
    public void addColumn(String description) {
        columnDao.addColumn(description);
    }

    @Override
    public List<Column> getAllColumn() {
        return columnDao.getAll();
    }

    @Override
    public void fixColumnMsg(int id,String name) {
        Column column=new Column(id,name);
        columnDao.fixMsg(column);
    }

    @Override
    public Object getColumnPsg(int columnId) {
        List<Integer> blogId=blogColumnDao.getMsgByColumn(columnId);
        List<Blog> blogList=blogDao.getMsgByBlogIdList(blogId);
        return blogList;
    }

    @Override
    public void deleteColumnById(int columnId) {
        blogDao.deleteByColumn(columnId);
    }

    @Override
    public void addPsgToColumn(int psgId, int columnId) {
        blogColumnDao.addPsgToColumn(new BlogColumn(psgId,columnId));
    }
}
