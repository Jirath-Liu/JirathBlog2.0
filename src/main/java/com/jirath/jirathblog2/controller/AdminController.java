package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.service.BlogContentService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客管理相关，需要权限
 * @author Jirath
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    BlogContentService blogContentService;
    @Autowired
    MsgValueUtil msgValueUtil;

    /**
     * 添加文章
     * @param blog 文章的内容
     * @return
     */
    @RequestMapping("/blog/add")
    @ResponseBody
    public Object addPassage(Blog blog){
        try {
            blogContentService.addPassage(blog);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("addPassage")
                    .data(blog)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("AddError")
                    .data(null)
                    .build();
        }

    }

    @Transactional
    @RequestMapping("/blog/delete/{blogId}")
    public Object deleteBlog(@PathVariable int blogId) {
        try {
            blogContentService.delete(blogId);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("delete")
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("deleteError")
                    .build();
        }

    }

    /**
     * 只可修改标题，作者，内容
     * @param blog
     * @return
     */
    @RequestMapping("/blog/fix")
    public Object fixBlog(Blog blog){
        try {
            blogContentService.fix(blog);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("fix")
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("fixError")
                    .build();
        }
    }
}
