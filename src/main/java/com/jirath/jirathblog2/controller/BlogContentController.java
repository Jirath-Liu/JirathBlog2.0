package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.pojo.Blog;
import com.jirath.jirathblog2.service.ColumnService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.service.BlogContentService;
import com.jirath.jirathblog2.vo.DefaultPageMsg;
import com.jirath.jirathblog2.vo.PageMsg;
import com.jirath.jirathblog2.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客内容的获取，不需要权限
 * @author Jirath
 */
@Controller
@RequestMapping("/blog")
@ResponseBody
public class BlogContentController {
    private final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    BlogContentService blogContentService;
    @Autowired
    ColumnService columnService;
    @RequestMapping("/{blogId}")
    public Object getPsgById(@PathVariable int blogId){
        try {
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg(blogId+"content")
                    .data(blogContentService.getPsgById(blogId))
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("contentError")
                    .data(null)
                    .build();
        }

    }
    @RequestMapping("/defaultPage")
    @ResponseBody
    public Object getDefaultPageContent(){
        try {
            DefaultPageMsg defaultPageMsg = blogContentService.getDefaultPageContent();
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("defaultPage")
                    .data(defaultPageMsg)
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("defaultPageError")
                    .data(null)
                    .build();
        }

    }
    @ResponseBody
    @RequestMapping("/page/{pageId}")
    public Object getSpecificPage(@PathVariable int pageId){
        try {
            PageMsg pageMsg= blogContentService.getSpecificPage(pageId);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("SpecificPage")
                    .data(pageMsg)
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("getSpecificPageError")
                    .data(null)
                    .build();
        }
    }

    /**
     * 获取随机的文章，作为推荐
     * @return
     */
    @ResponseBody
    @RequestMapping("/recommend")
    public Object getRecommendPsg(){
        try {
            Blog blog = blogContentService.getRand();
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("recommend")
                    .data(blog)
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("RecommendError")
                    .data(null)
                    .build();
        }

    }
    @ResponseBody
    @RequestMapping("/latest")
    public Object getLatestPsg(){
        try {
            Blog blog = blogContentService.getLatest();
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("latest")
                    .data(blog)
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("LatestError")
                    .data(null)
                    .build();
        }

    }


}
