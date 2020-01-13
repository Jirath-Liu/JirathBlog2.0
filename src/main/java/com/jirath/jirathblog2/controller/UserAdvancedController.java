package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.pojo.Comment;
import com.jirath.jirathblog2.service.CommentService;
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
 * 高级用户操作，需要登录,不需要做登录检查
 * @author Jirath
 */
@Controller
@RequestMapping("/user")
@ResponseBody
public class UserAdvancedController {
    private final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    CommentService commentService;

    /**
     * 评论
     * @param comment
     * @return
     */
    @Transactional
    @RequestMapping("/comment")
    public Object addComment(Comment comment){
        try {
            commentService.comment(comment);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("comment")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("commentError")
                    .build();
        }

    }
    @RequestMapping("/comment/delete/{commentId}")
    public Object addComment(@PathVariable int commentId){
        try {
            commentService.delComment(commentId);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("comment")
                    .build();
        }catch (Exception e){
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("commentError")
                    .build();
        }

    }
}
