package com.lianrf.tierexp.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * ExceptionUtil
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/14 9:16 上午
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static Throwable unwrapThrowable(Throwable wrapped) {
        Throwable unwrapped = wrapped;
        if (unwrapped instanceof InvocationTargetException) {
            unwrapped = ((InvocationTargetException) unwrapped).getTargetException();
        } else if (unwrapped instanceof UndeclaredThrowableException) {
            unwrapped = ((UndeclaredThrowableException) unwrapped).getUndeclaredThrowable();
        }
        return unwrapped;
    }
}
