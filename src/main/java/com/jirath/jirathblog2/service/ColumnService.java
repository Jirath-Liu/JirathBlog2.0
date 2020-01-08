package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.Column;

import java.util.List;

/**
 * @author Jirath
 */
public interface ColumnService {
    /**
     * 添加分类
     * @param description 描述/分类名
     */
    void addColumn(String description);

    /**
     * 获取所有分类名
     * @return
     */
    List<Column> getAllColumn();

    /**
     * 修改分类信息
     * @param name 新名称
     * @param id 文章号
     */
    void fixColumnMsg(int id,String name);
}
