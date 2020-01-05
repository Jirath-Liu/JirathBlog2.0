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
     * @param blog 文章的内容，文章的日期，评论数量会在dao层自动的利用mysql函数完成
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
     * @param blogId 博客的数据库号
     */
    void deleteBlogById(int blogId);

    /**
     * 修改文章内容，最后修改时间使用数据库函数，
     * Id,评论数量，创作时间不变，
     * @param blog
     */
    void fixBlog(Blog blog);

    /**
     * 获取文章的评论数量
     * @param blogId
     * @return
     */
    int getCommentNum(int blogId);

    /**
     * 增加评论数
     * @param blogId
     * @return
     */
    int fixCommentNum(int blogId);
}
