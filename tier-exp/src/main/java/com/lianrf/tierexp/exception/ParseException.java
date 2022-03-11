package com.lianrf.tierexp.exception;

/**
 * TierExp 解析异常
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:15 下午
 */
public class ParseException extends RuntimeException {

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
