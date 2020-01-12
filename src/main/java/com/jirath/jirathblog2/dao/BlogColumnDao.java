package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.BlogColumn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface BlogColumnDao {
    /**
     * 根据文章Id删除记录
     * @param blogId
     */
    void deleteByBlogId(int blogId);

    /**
     * 获取所有信息
     * @return
     */
    List<BlogColumn> getAll();
    /**
     * 根据分类id找内容
     * @param columnId 分类号
     * @return 信息,IntegerList
     */
    List<Integer> getMsgByColumn(int columnId);

    /**
     * 添加博客-分类记录
     * @param blogColumn pojo
     */
    void addPsgToColumn(BlogColumn blogColumn);
    
}
