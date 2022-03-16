package com.lianrf.tierexp.exception;

/**
 * TierExp 解析异常
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:15 下午
 */
public class TierParseException extends TierException {

    public TierParseException() {
    }

    public TierParseException(String message) {
        super(message);
    }

    public TierParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
