package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.query.PageScope;
import com.jirath.jirathblog2.pojo.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 */
@Repository
public interface BlogDao {
    /**
     * 获取指定排序范围的blog
     * @param pageScope 页面范围，skipNum为跳过的数目，requireNum为需要的数目
     * @return 指定范围的博客的全部信息
     */
    List<Blog> getSpecificPage(PageScope pageScope);

    /**
     * 获取博客数目
     * @return 文章数目
     */
    int getBlogNum();

    /**
     * 添加文章，并返回文章号
     * @return 新建的文章号
     */
    int addPassage(Blog blog);

    /**
     * 获取最新记录
     * @return 最后一条文章记录
     */
    Blog getLatest();

    /**
     * 取一个随机的文章
     * @return 文章内容
     */
    Blog getRandPsg();

    /**
     * 根据博客Id删除博客
     */
    void deleteBlogById(int blogId);
}
