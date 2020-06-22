package com.jirath.jirathblog2.util;

/**
 * @author Jirath
 * @date 2020/6/22
 * @description:
 */
public class SystemSettingUtil {
    public static final int shiroHashInteractions=2;
    private static int defaultColumn=1;

    public static int getDefaultColumn() {
        return defaultColumn;
    }

    public static void setDefaultColumn(int defaultColumn) {
        SystemSettingUtil.defaultColumn = defaultColumn;
    }
}
