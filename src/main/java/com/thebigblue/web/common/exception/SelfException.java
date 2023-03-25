package com.thebigblue.web.common.exception;

/**
 * 数据部自定义异常
 */
public class SelfException extends RuntimeException {

    public SelfException(String message) {
        super(message);
    }
}
