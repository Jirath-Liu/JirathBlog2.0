package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.service.ColumnService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        try {
            columnService.getAllColumn();
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("allColumn")
                    .build();
        } catch (Exception e) {
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("getColumnError")
                    .build();
        }
    }

    /**
     * 获取指定分类的博文内容
     * @param columnId 分类号
     * @return 内容，包含博客的完整信息
     */
    @RequestMapping("/Psg/{columnId}")
    public Object getSpecificColumn(@PathVariable int columnId){
        try {
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .data(columnService.getColumnPsg(columnId))
                    .msg(columnId+"content")
                    .build();
        } catch (Exception e) {
            logger.error("异常",e);
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("getError")
                    .build();
        }
    }
}
