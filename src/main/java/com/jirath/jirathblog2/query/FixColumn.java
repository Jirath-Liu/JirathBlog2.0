package com.jirath.jirathblog2.query;

import lombok.Data;

/**
 * @author Jirath
 * @date 2020/6/22
 * @description:
 */
@Data
public class FixColumn {
    private Integer originColumnId;
    private Integer newColumnId;

    public FixColumn(Integer originColumnId, Integer newColumnId) {
        this.originColumnId = originColumnId;
        this.newColumnId = newColumnId;
    }
}
