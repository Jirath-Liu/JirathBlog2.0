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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客管理相关，需要权限
 * @author Jirath
 */
@RequestMapping("/admin")
@Controller
@ResponseBody
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    BlogContentService blogContentService;
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    ColumnService columnService;
    @Autowired
    TagService tagService;
    /**
     * ======================================================================================
     *                          文章相关
     * ======================================================================================
     */

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
            logger.info("添加文章："+blog.toString());
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("addPassage")
                    .data(blog)
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
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
            logger.error("异常",e);
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
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("fixError")
                    .build();
        }
    }
    @RequestMapping("/blog/column/add/{blogId}/{columnId}")
    public Object addPsgToColumn(@PathVariable int blogId,@PathVariable int columnId){
        try {
            columnService.addPsgToColumn(blogId,columnId);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("add")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("addError")
                    .build();
        }
    }
    /**
     * ======================================================================================
     *                          分类/专栏相关
     * ======================================================================================
     */
    @RequestMapping("/column/delete/{columnId}")
    public Object deleteColumn(@PathVariable int columnId){
        try {
            columnService.deleteColumnById(columnId);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("addColumn")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("addError")
                    .build();
        }
    }
    @RequestMapping("/column/add")
    public Object addColumn(@PathVariable String presentation){
        try {
            columnService.addColumn(presentation);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("addColumn")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("addError")
                    .build();
        }
    }
    @RequestMapping("/column/fix/{columnId}/{name}")
    public Object fixColumn(@PathVariable int columnId,@PathVariable String name){
        try {
            columnService.fixColumnMsg(columnId,name);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("addColumn")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("addError")
                    .build();
        }
    }
    /*
    ===================================================================================
                                 专栏相关
    ===================================================================================
     */
    @RequestMapping("/tag/add")
    public Object addTag(Tag tag){
        try {
            tagService.addTag(tag);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("newTag")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("newTagError")
                    .build();
        }
    }
    @RequestMapping("/blog/tag")
    public Object addPsgToTag(BlogTag blogTag){
        try {
            tagService.addPsgToTag(blogTag);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("addTag")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("addError")
                    .build();
        }
    }
    @RequestMapping("/blog/delTag")
    public Object delPsgInTag(BlogTag blogTag){
        try {
            tagService.delBlogInTag(blogTag);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("delBlogTag")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("delError")
                    .build();
        }
    }
    @RequestMapping("/tag/delete/{tagId}")
    public Object delTag(@PathVariable int tagId){
        try {
            tagService.delTag(tagId);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("delTag")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("delError")
                    .build();
        }
    }
    @RequestMapping("/tag/fix")
    public Object fixTag(Tag tag){
        try {
            tagService.fixTag(tag);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("fixTag")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("fixError")
                    .build();
        }
    }
}
