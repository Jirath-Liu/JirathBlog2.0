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
    @RequestMapping("/add")
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
    @RequestMapping("/delete/{blogId}")
    public Object deleteBlog(@PathVariable int blogId) {
        blogContentService.delete(blogId);
        return ResultVo.builder().build();
    }
}
