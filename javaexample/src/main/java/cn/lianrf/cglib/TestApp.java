package cn.lianrf.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.io.*;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class TestApp {
    public static void main(String[] args){

    }

    private static void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        //0:拦截 1：不做任何事 2：固定值
        enhancer.setCallbacks(new Callback[]{new TargetInterceptor(), NoOp.INSTANCE,new TargetResultFixed()});
        enhancer.setCallbackFilter(new TargetMethodCallbackFilter());
        TargetObject o =(TargetObject) enhancer.create();


        String s0 = o.show1();
        System.out.println(s0+":---over");
        String s1 = o.show2();
        System.out.println(s1+":---over");
        String s2 = o.show3();
        System.out.println(s2+":---over");
    }

    private static void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargetInterceptor());

        TargetObject o =(TargetObject) enhancer.create();
        o.show1();
    }
}
