package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.BlogTag;
import com.jirath.jirathblog2.pojo.Tag;
import com.jirath.jirathblog2.vo.BlogSimpleVo;
import com.jirath.jirathblog2.vo.BlogVo;

import java.util.List;

/**
 * @author Jirath
 */
public interface TagService {
    /**
     * 加标签
     * @param tag
     */
    void addTag(Tag tag);

    /**
     * 加文章-标签
     * @param blogTag
     */
    void addPsgToTag(BlogTag blogTag);

    /**
     * 删标签
     * @param tagId
     */
    void delTag(int tagId);

    /**
     * 改标签
     * @param tag
     */
    void fixTag(Tag tag);

    /**
     * 删文章-标签
     * @param blogTag
     */
    void delBlogInTag(BlogTag blogTag);

    /**
     * 所有标签
     * @return
     */
    List<Tag> getAll();
    List<BlogSimpleVo> getPsgByTagId(Integer tagId);

    List<Tag> getByBlogId(Integer blogId);

    void delByColumnId(int columnId);

    void setTagListToBlogVoList(List<BlogVo> list);
}
