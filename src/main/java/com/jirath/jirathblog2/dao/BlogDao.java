package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.query.PageScope;
import com.jirath.jirathblog2.pojo.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blog表对应操作，最新更新：文章输出排序ORDER BY blog_id desc
 * @author Jirath
 * @since 2020年1月16日11:31:51
 */
@Repository
public interface BlogDao {
    /**
     * 根据分类删除文章，包含子查询分类信息
     * @param columnId
     */
    void deleteByColumn(int columnId);
    /**
     * 根据文章号获取内容
     * @param blogIdList 文章号
     * @return 完整内容
     */
    List<Blog> getMsgByBlogIdList(List<Integer> blogIdList);
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
    Integer getBlogNum();

    /**
     * 添加文章，并返回文章号
     * @param blog 文章的内容，文章的日期，评论数量会在dao层自动的利用mysql函数完成
     * @return 新建的文章号
     */
    Integer addPassage(Blog blog);

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
    Integer getCommentNum(int blogId);

    /**
     * 增加评论数
     * @param blogId
     * @return
     */
    Integer fixCommentNum(int blogId);

    /**
     * 增加访问次数
     * @param blogId
     */
    void fixVisitNum(int blogId);
    /**
     * 由id获取文章信息
     * @param id 文章id号
     * @return 文章信息
     */
    Blog getMsgById(int id);

    void deleteBlogByIdList(List<Integer> ids);

    void fixBlogAT(Blog blog);
}
