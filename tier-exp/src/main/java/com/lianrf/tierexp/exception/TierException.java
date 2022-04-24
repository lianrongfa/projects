package com.lianrf.tierexp.exception;

/**
 * 异常
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:15 下午
 */
public class TierException extends RuntimeException {

    public TierException() {
    }

    public TierException(Throwable cause) {
        super(cause);
    }

    public TierException(String message) {
        super(message);
    }

    public TierException(String message, Throwable cause) {
        super(message, cause);
    }
}
