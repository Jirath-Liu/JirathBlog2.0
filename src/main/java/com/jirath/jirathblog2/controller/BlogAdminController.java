package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.pojo.BlogTag;
import com.jirath.jirathblog2.pojo.Tag;
import com.jirath.jirathblog2.service.BlogContentService;
import com.jirath.jirathblog2.service.ColumnService;
import com.jirath.jirathblog2.service.TagService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客管理相关，需要权限
 * @author Jirath
 */
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/admin")
@Controller
@ResponseBody
public class BlogAdminController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    BlogContentService blogContentService;
    @Autowired
    MsgValueUtil msgValueUtil;

    /**
     * 添加文章
     *
     * @param blog 文章的内容
     * @return
     */
    @RequestMapping("/blog/add")
    @ResponseBody
    public Object addPassage(Blog blog) {
        Blog blogNew = blogContentService.addPassage(blog);
        logger.info("添加文章：" + blog.toString());
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("addPassage")
                .data(blogNew)
                .build();
    }


    @RequestMapping(value = "/blog/delete/{blogId}", method = RequestMethod.DELETE)
    public ResultVo deleteBlog(@PathVariable int blogId) {
        blogContentService.delete(blogId);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("delete")
                .build();

    }

    @RequestMapping(value = "blog/deleteList",method = RequestMethod.POST)
    public ResultVo delBlogList(List<Integer> ids){
        blogContentService.delete(ids);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("delete")
                .build();
    }

    /**
     * 只可修改标题，作者，内容
     *
     * @param blog
     * @return
     */
    @RequestMapping("/blog/fix")
    public Object fixBlog(Blog blog) {
        blogContentService.fix(blog);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("fix")
                .build();
    }

    /**
     * 修改作者和标题
     * @param title
     * @return
     */
    @RequestMapping("/blog/fixatc")
    public Object fixBlogAT(@RequestParam Integer id, @RequestParam String title, @RequestParam String author,@RequestParam Integer columnId) {
        blogContentService.fixATC(id, title, author,columnId);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("fix")
                .build();
    }

}
