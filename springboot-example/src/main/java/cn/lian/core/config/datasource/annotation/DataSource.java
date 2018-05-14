package cn.lian.core.config.datasource.annotation;

import cn.lian.core.config.datasource.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lianrongfa on 2018/5/7.
 * 数据源注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.MASTER;
}
