package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.pojo.BlogTag;
import com.jirath.jirathblog2.pojo.Tag;
import com.jirath.jirathblog2.service.TagService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jirath
 * @date 2020/4/23
 * @description:
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    TagService tagService;
    @Autowired
    MsgValueUtil msgValueUtil;

    @RequestMapping("/all")
    public Object getAllTag(){
        return ResultVo.builder()
                .data(tagService.getAll())
                .code(msgValueUtil.getSuccess())
                .msg("allTag")
                .build();
    }

    @RequestMapping("/blog/{tagId}")
    public Object getBlogsByTag(@PathVariable Integer tagId){
        return ResultVo.builder()
                .data(tagService.getPsgByTagId(tagId))
                .code(msgValueUtil.getSuccess())
                .msg("allTag")
                .build();
    }
    @RequestMapping("/search/{blogId}")
    public Object getBlogTags(@PathVariable Integer blogId){
        return ResultVo.builder()
                .data(tagService.getByBlogId(blogId))
                .code(msgValueUtil.getSuccess())
                .msg("allTag")
                .build();
    }

    @RequestMapping("/add")
    public Object addTag(Tag tag){
        tagService.addTag(tag);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("newTag")
                .build();
    }
    @RequestMapping("/blog/tag")
    public Object addPsgToTag(BlogTag blogTag){
        tagService.addPsgToTag(blogTag);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("addTag")
                .build();
    }
    @RequestMapping("/blog/delTag")
    public Object delPsgInTag(BlogTag blogTag){
        tagService.delBlogInTag(blogTag);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("delBlogTag")
                .build();

    }
    @RequestMapping("delete/{tagId}")
    public Object delTag(@PathVariable int tagId){
        tagService.delTag(tagId);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("delTag")
                .build();
    }
    @RequestMapping("fix")
    public Object fixTag(Tag tag){
        tagService.fixTag(tag);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("fixTag")
                .build();
    }
}
