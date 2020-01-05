package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.vo.DefaultPageMsg;

import java.util.List;

/**
 * @author Jirath
 */
public interface BlogContentService {
    /**
     * 获取首页的信息，前五条
     * @return 首页博客信息
     */
    DefaultPageMsg getDefaultPageContent();

    /**
     * 获取指定页数的博客,默认为5个每页
     * @param page 页数
     * @return 博客内容
     */
    List<Blog> getSpecificPage(int page);

    /**
     * 添加文章
     * @param blog 文章信息
     */
    void addPassage(Blog blog);

    /**
     * 查找最新的文章
     * @return 最新文章
     */
    Blog getLatest();

    /**
     * 获取一个随机的文章
     * @return 文章内容
     */
    Blog getRand();

    /**
     * 删除一个文章，
     * 需要删除相关的评论，使用Spring事务机制。
     * @param blogId
     */
    void delete(int blogId);

    /**
     * 修改文章内容，全部替换
     * @param blog 文章内容
     */
    void fix(Blog blog);
}
