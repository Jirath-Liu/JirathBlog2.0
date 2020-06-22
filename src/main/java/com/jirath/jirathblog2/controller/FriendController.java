package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.dao.FriendDao;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jirath
 * @date 2020/6/9
 * @description:
 */
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendDao friendDao;
    @Autowired
    MsgValueUtil msgValueUtil;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultVo getAllFriends(){
        return ResultVo.builder()
                .data(friendDao.getAll())
                .code(msgValueUtil.getSuccess())
                .build();
    }
}
