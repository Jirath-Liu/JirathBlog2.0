package com.jirath.jirathblog2.exception;

/**
 * @author Jirath
 */
public class ExistedAccountException extends Exception {
    public ExistedAccountException(String errorMsg) {
        super(errorMsg);
    }
}
