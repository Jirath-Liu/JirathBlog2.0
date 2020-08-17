package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.Column;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface ColumnDao {
    /**
     * 添加
     * @param description
     */
    void addColumn(String description);

    /**
     * 获取所有的分类
     * @return 所有分类
     */
    List<Column> getAll();

    /**
     * 修改信息
     * @param column 信息
     */
    void fixMsg(Column column);

    @Delete("DELETE FROM `column` WHERE column_id = #{value}")
    void deleteColumn(int columnId);
}
