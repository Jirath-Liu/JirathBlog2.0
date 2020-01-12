package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface TagDao {
    /**
     * 加标签
     * @param tag
     */
    void newTag(Tag tag);

    /**
     * 删标签
     * @param tagId
     */
    void delTag(int tagId);

    /**
     * 修改标签
     * @param tag
     */
    void fixTag(Tag tag);

    /**
     * 所有标签
     * @return
     */
    List<Tag> allTag();

    /**
     * 根据id获取内容
     * @param tagId
     * @return
     */
    Tag getMsgById(int tagId);
}
