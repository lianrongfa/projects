package com.lianrf.tierexp.exception;

/**
 * 异常
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/2/24 3:15 下午
 */
public class TierRunException extends TierException {

    public TierRunException() {
    }

    public TierRunException(String message) {
        super(message);
    }

    public TierRunException(String message, Throwable cause) {
        super(message, cause);
    }
}
