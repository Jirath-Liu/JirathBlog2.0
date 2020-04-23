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
@RestController("/tag")
public class TagController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    TagService tagService;
    @Autowired
    MsgValueUtil msgValueUtil;

    @RequestMapping("/all")
    public Object getAllTag(){
        try {
            return ResultVo.builder()
                    .data(tagService.getAll())
                    .code(msgValueUtil.getSuccess())
                    .msg("allTag")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("newTagError")
                    .build();
        }
    }

    @RequestMapping("/search/{blogId}")
    public Object getBlogTag(@PathVariable Integer blogId){
        try {
            return ResultVo.builder()
                    .data(tagService.getByBlogId(blogId))
                    .code(msgValueUtil.getSuccess())
                    .msg("allTag")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("newTagError")
                    .build();
        }
    }

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
