package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.service.ColumnService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jirath
 */
@Controller
@RequestMapping("/column")
public class ColumnController {
    @Autowired
    ColumnService columnService;
    @Autowired
    MsgValueUtil msgValueUtil;

    @RequestMapping("/all")
    public Object getAllColumn() {
        try {
            columnService.getAllColumn();
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("addColumn")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("addError")
                    .build();
        }
    }
    @RequestMapping("/Psg/{columnId}")
    public Object getSpecificColumn(@PathVariable int columnId){
        try {

            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg(columnId+"content")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("addError")
                    .build();
        }
    }
}
