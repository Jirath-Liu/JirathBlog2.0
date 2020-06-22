package com.jirath.jirathblog2.conf.exception;

import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Jirath
 * @date 2020/6/21
 * @description:
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    MsgValueUtil msgValueUtil;
    private void logErrorRequestMsg(HttpServletRequest request){
        log.error("=========异常请求=========");
        log.error("请求信息：");
        log.error(request.getRequestURI());
        log.error(request.getParameterMap().toString());
        log.error("=========================");
    }



    @ExceptionHandler(value = Exception.class)
    public ResultVo defaultException(HttpServletRequest request,Exception e){
        log.error("异常："+e.getMessage());
        logErrorRequestMsg(request);
        log.error(Arrays.toString(e.getStackTrace()));
        return ResultVo.builder()
                .code(msgValueUtil.getDefaultError())
                .msg("系统错误")
                .build();
    }
}
