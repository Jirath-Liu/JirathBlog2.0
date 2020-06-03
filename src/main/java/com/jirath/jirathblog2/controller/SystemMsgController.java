package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.service.SystemService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Jirath
 * @date 2020/5/7
 * @description:
 */
@RestController
@RequestMapping(value = "sys")
public class SystemMsgController {
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/visit",method = RequestMethod.GET)
    public ResultVo getVisitTimes(){
        return ResultVo.builder()
                .data(systemService.getVisitTimes())
                .code(msgValueUtil.getSuccess())
                .build();
    }
    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public ResultVo getStartTime(){
        return ResultVo.builder()
                .data(systemService.getStartTime())
                .code(msgValueUtil.getSuccess())
                .build();
    }
}
