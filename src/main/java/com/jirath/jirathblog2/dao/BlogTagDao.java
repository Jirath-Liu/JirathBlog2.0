package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.BlogTag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface BlogTagDao {
    /**
     * 根据文章Id删除记录
     * @param blogId
     */
    void deleteByBlogId(int blogId);

    /**
     * 根据文章id获取信息
     * @param blogId
     * @return 标签id序列
     */
    List<Integer> getByBlogId(int blogId);

    /**
     * 标签下所有文章
     * @param tagId
     * @return 文章id序列
     */
    List<Integer> getByTagId(int tagId);
    /**
     * 根据标签删记录
     * @param tagId
     */
    void delByTag(int tagId);

    /**
     * 添加记录
     * @param blogTag 信息
     */
    void addMsg(BlogTag blogTag);

    /**
     * 删除特定记录
     * @param blogTag
     */
    void delSpecificRecord(BlogTag blogTag);
}
