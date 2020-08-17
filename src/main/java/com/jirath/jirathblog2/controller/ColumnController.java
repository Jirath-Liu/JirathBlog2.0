package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.service.ColumnService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Can be accessed by all
 * @author Jirath
 */
@Controller
@RequestMapping("/column")
@ResponseBody
public class ColumnController {
    private final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    ColumnService columnService;
    @Autowired
    MsgValueUtil msgValueUtil;

    @RequestMapping("/all")
    public Object getAllColumn() {
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .data(columnService.getAllColumn())
                .msg("allColumn")
                .build();
    }

    /**
     * 获取指定分类的博文内容
     * @param columnId 分类号
     * @return 内容，包含博客的完整信息
     */
    @RequestMapping("/{columnId}")
    public Object getSpecificColumn(@PathVariable int columnId){
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .data(columnService.getColumnPsg(columnId))
                .msg(columnId + "content")
                .build();
    }
    /**
     * ======================================================================================
     *                          管理
     * ======================================================================================
     */
    @RequestMapping("/delete/{columnId}")
    public Object deleteColumn(@PathVariable int columnId){
        columnService.deleteColumnById(columnId);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("delColumn")
                .build();

    }
    @RequestMapping(value = "/add/{name}",method = RequestMethod.POST)
    public Object addColumn(@PathVariable String name){
        columnService.addColumn(name);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("addColumn")
                .build();
    }
    @RequestMapping(value = "/fix/{columnId}/{name}")
    public Object fixColumn(@PathVariable int columnId,@PathVariable String name){
        columnService.fixColumnMsg(columnId,name);
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("addColumn")
                .build();
    }
}
