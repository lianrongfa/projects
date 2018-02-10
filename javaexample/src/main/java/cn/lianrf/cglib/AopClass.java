package cn.lianrf.cglib;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

/**
 * Created by lianrongfa on 2017/11/6.
 */
@Component
@Aspect
public class AopClass {

    @Pointcut("@annotation(cn.lianrf.cglib.MyAop)")
    public void ipoint(){

    }
    @Around(value = "ipoint()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("-------------");
        point.proceed();
        System.out.println("-------------");
    }
}
