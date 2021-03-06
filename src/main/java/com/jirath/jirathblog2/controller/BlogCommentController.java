package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.service.CommentService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jirath
 * @date 2020/4/24
 * @description:
 */
@RestController
@RequestMapping("comment")
public class BlogCommentController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    MsgValueUtil msgValueUtil;

    @Autowired
    CommentService commentService;

    @GetMapping("/blog/{blogId}")
    public Object getPsgComment(@PathVariable Integer blogId){
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .data(commentService.getPsgComment(blogId))
                .msg("blogComment")
                .build();
    }

}
