package cn.lianrf.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class TargetInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("----------start");
        Object resut = methodProxy.invokeSuper(o, objects);
        System.out.println("------------end");
        return resut;
    }
}
