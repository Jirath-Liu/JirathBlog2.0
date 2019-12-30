package com.jirath.jirathblog2.query;

import lombok.Data;

/**
 * @author Jirath
 */
@Data
public class PageScope {
    private int skipNum;
    private int requireNum;

    public PageScope(int skipNum, int requireNum) {
        this.skipNum = skipNum;
        this.requireNum = requireNum;
    }
}
