package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.vo.BlogVo;
import com.jirath.jirathblog2.vo.DefaultPageMsg;
import com.jirath.jirathblog2.vo.PageMsg;

import java.util.List;

/**
 * @author Jirath
 */
public interface BlogContentService {
    /**
     * 根据文章号获取内容
     * @param id 文章号
     * @return 文章内容
     */
    BlogVo getPsgById(int id);
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
    PageMsg getSpecificPage(int page);

    /**
     * 添加文章
     * @param blog 文章信息
     */
    Blog addPassage(Blog blog);

    /**
     * 查找最新的文章
     * @return 最新文章
     */
    BlogVo getLatest();

    /**
     * 获取一个随机的文章
     * @return 文章内容
     */
    BlogVo getRand();

    /**
     * 删除一个文章，
     * 需要删除相关的评论，使用Spring事务机制。
     * @param blogId
     */
    void delete(int blogId);

    void delete(List<Integer> ids);
    /**
     * 修改文章内容，全部替换
     * @param blog 文章内容
     */
    void fix(Blog blog);

    /**
     * 修改标题与作者
     * @param id
     * @param title
     * @param author
     */
    void fixATC(Integer id,String title,String author,Integer column);
}
