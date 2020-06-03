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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * 高级用户操作，需要登录,不需要做登录检查
 * @author Jirath
 */
@Controller
@ResponseBody
public class CommentController {
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
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResultVo addComment(Comment comment){
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

    /**
     * // TODO: 2020/6/3 接口删除还没有完善，需要完善 
     * @param commentId
     * @return
     */
    @RequestMapping("/comment/delete/")
    public Object addComment(@PathVariable int commentId){
        try {
            Boolean canDel= commentService.commentCanDel(commentId);
            if(canDel){
                commentService.delComment(commentId);
                return ResultVo.builder()
                        .code(msgValueUtil.getSuccess())
                        .msg("del comment")
                        .build();
            }
            return ResultVo.builder()
                    .code(msgValueUtil.getNoPower())
                    .msg("del comment")
                    .build();
        }catch (Exception e){
            logger.error("异常"+e.getMessage(),e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("commentError")
                    .build();
        }

    }
}
