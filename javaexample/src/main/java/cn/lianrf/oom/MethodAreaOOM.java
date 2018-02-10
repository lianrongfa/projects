package cn.lianrf.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 1.8 -XX:MaxMetaspaceSize=1M 元空间 1.8以后永久代移入元空间
 * 一个新的参数 (MaxMetaspaceSize)可以使用。允许你来限制用于类元数据的本地内存。
 * 如果没有特别指定，元空间将会根据应用程序在运行时的需求动态设置大小。
 *
 * 1.7 -XX:PermSize=10M -XX:MaxPermSize=10M 方法区
 * Created by lianrongfa on 2018/1/9.
 */
public class MethodAreaOOM {
    public static void main(String[] args) {
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(obj,args);
                }
            });
            enhancer.create();
        }
    }
    static class OOMObject{}
}
