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
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg(blogId + "content")
                .data(blogContentService.getPsgById(blogId))
                .build();
    }
    @RequestMapping("/defaultPage")
    @ResponseBody
    public Object getDefaultPageContent(){
        DefaultPageMsg defaultPageMsg = blogContentService.getDefaultPageContent();
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("defaultPage")
                .data(defaultPageMsg)
                .build();
    }
    @ResponseBody
    @RequestMapping("/page/{pageId}")
    public Object getSpecificPage(@PathVariable int pageId){
        PageMsg pageMsg = blogContentService.getSpecificPage(pageId);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("SpecificPage")
                .data(pageMsg)
                .build();
    }

    /**
     * 获取随机的文章，作为推荐
     * @return
     */
    @ResponseBody
    @RequestMapping("/recommend")
    public ResultVo getRecommendPsg(){
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("recommend")
                .data(blogContentService.getRand())
                .build();
    }
    @ResponseBody
    @RequestMapping("/latest")
    public Object getLatestPsg(){
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("latest")
                .data(blogContentService.getLatest())
                .build();
    }


}
