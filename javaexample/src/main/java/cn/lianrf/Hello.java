package cn.lianrf;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;


/**
 * Created by lianrongfa on 2018/1/2.
 */
public class Hello {
    public static void main(String[] args) throws Exception {
        MethodType type = MethodType.methodType(int.class, Aa1.class);
        Hello o = new Hello();
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Hello.class, "test1", type);
        Object invoke=null;
        try {
            invoke= methodHandle.invoke(o,new Aa1());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println();
    }
    static class Aa1{}
    static class Aa2 extends Aa1{}
    static class Aa3 extends Aa1{}
    public int test1(Aa1 a){
        System.out.println("test1");
        return 1;
    }
    public void test1(Aa2 a){
        System.out.println("test2");
    }
    public void test1(Aa3 a){
        System.out.println("test3");
    }

}

