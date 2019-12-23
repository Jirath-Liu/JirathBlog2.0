package com.jirath.jirathblog2.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVo {
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
}
