package cn.lian.core.config.datasource.aspect;


import cn.lian.core.config.datasource.DataSourceEnum;
import cn.lian.core.config.datasource.DynamicDatasource;
import cn.lian.core.config.datasource.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lianrongfa on 2018/5/7.
 */
@Component
@Aspect
public class DataSourceAspect {

    @Pointcut("@annotation(cn.lian.core.config.datasource.annotation.DataSource)")
    public void point() {
    }

    @Before("point()")
    public void setDataSource(JoinPoint joinPoint) {

        MethodSignature signature =(MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        DataSourceEnum dataSourceEnum = annotation.value();
        //判断是否有改注解的DataSource
        boolean b = DataSourceEnum.contains(dataSourceEnum);
        if(b){
            DynamicDatasource.setDataSource(dataSourceEnum.getName());
        }

    }
}
