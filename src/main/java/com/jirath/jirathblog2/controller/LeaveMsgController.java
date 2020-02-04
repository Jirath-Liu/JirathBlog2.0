package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.dao.LeaveMsgDao;
import com.jirath.jirathblog2.pojo.LeaveMsg;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jirath
 */
@RequestMapping("/leaveMsg")
@RestController
public class LeaveMsgController {
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    LeaveMsgDao leaveMsgDao;
    @RequestMapping("/add")
    public ResultVo addMsg(String msg) {
        leaveMsgDao.addMsg(msg);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("add msg "+msg)
                .build();
    }
    @RequestMapping("/del")
    public ResultVo delMsg(int id) {
        leaveMsgDao.deleMsg(id);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("del msg "+id)
                .build();
    }
    @RequestMapping("/all")
    public ResultVo getAll(){
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .data(leaveMsgDao.getAll())
                .msg("all leave msg")
                .build();
    }
}
