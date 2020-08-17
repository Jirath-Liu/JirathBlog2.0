package com.jirath.jirathblog2.conf.exception;

import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

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
        StringBuffer buffer=new StringBuffer();
        Map<String, String[]> map=request.getParameterMap();
        buffer.append("请求参数: [ ");
        for (Map.Entry<String, String[]> entry : map.entrySet()){
            buffer.append("{");
            buffer.append(entry.getKey());
            buffer.append(" : ");
            buffer.append(entry.getValue());
            buffer.append("}");
        }
        buffer.append(" ]");
        log.error(buffer.toString());
        log.error("=========================");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo defaultException(HttpServletRequest request,Exception e){
        log.error("异常："+e.getMessage());
        logErrorRequestMsg(request);
//        log.error(Arrays.toString(e.getStackTrace()));
        e.printStackTrace();
        return ResultVo.builder()
                .code(msgValueUtil.getDefaultError())
                .msg("系统错误")
                .build();
    }
}
