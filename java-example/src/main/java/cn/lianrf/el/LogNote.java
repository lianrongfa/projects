package cn.lianrf.el;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记日志操作
 * @version: v1.0
 * @date: 2020/1/10
 * @author: lianrf
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogNote {
    String value();
}
