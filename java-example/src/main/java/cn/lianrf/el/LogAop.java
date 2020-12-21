package cn.lianrf.el;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @version: v1.0
 * @date: 2020/1/13
 * @author: lianrf
 */
@Aspect
@Component
public class LogAop implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Pointcut("@annotation(cn.lianrf.el.LogNote)")
    public void point() {
    }

    @Around(value = "point()")
    public void before(ProceedingJoinPoint joinPoint) {
        try {
            MethodInvocationProceedingJoinPoint point = (MethodInvocationProceedingJoinPoint) joinPoint;
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            Object[] args = joinPoint.getArgs();
            Object target = point.getTarget();
            Class<?> targetClass = target.getClass();
            logNoteProcess(method,args,target,targetClass);
            joinPoint.proceed();

        } catch (Exception e) {
            //log.error("LogAop error",e);
        } catch (Throwable throwable) {
            //log.error("调用异常",throwable);
            throw new RuntimeException(throwable);
        }
    }

    private void logNoteProcess(Method method, Object[] args, Object target, Class<?> targetClass) {
        LogExpressionEvaluator logExpressionEvaluator = new LogExpressionEvaluator();
        EvaluationContext evaluationContext = logExpressionEvaluator.createEvaluationContext(method, args, target, targetClass, method, new Object(), this.applicationContext);
        AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method, targetClass);

        HashMap<LogExpressionEvaluator.ExpressionKey, Expression> map = new HashMap<>();
        Object value = logExpressionEvaluator.getExpression(map, annotatedElementKey, "#dto.name").getValue(evaluationContext);

        System.out.println(value);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
