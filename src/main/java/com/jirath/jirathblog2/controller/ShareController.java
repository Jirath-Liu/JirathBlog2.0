package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.pojo.Share;
import com.jirath.jirathblog2.service.ShareService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jirath
 * @date 2020/6/30
 * @description:
 */
@RestController
@RequestMapping("/share")
public class ShareController {
    @Autowired
    ShareService shareService;
    @Autowired
    MsgValueUtil msgValueUtil;

    @RequestMapping("/all")
    public ResultVo getAll() {
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .data(shareService.getAll())
                .build();
    }
    @RequestMapping("/add")
    public ResultVo add(@RequestBody Share share) {
        shareService.add(share);
        return ResultVo.builder().code(msgValueUtil.getSuccess()).build();
    }
    @RequestMapping("/del")
    public ResultVo del(Integer id) {
        shareService.del(id);
        return ResultVo.builder().code(msgValueUtil.getSuccess()).build();
    }
    @RequestMapping("/update")
    public ResultVo update(@RequestBody Share share) {
        shareService.updateMsg(share);
        return ResultVo.builder().code(msgValueUtil.getSuccess()).build();
    }
}
