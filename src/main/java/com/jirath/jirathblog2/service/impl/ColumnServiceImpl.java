package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.ColumnDao;
import com.jirath.jirathblog2.pojo.Column;
import com.jirath.jirathblog2.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    ColumnDao columnDao;
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
}
