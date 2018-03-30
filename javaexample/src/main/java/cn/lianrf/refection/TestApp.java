package cn.lianrf.refection;

import java.lang.reflect.Method;

/**
 * Created by lianrongfa on 2018/3/30.
 */
public class TestApp {
    public static void main(String[] args) {
        SonObject sonObject = new SonObject();
        try {
            Method method = SonObject.class.getMethod("fatherMethod", null);

            Class<?> aClass = method.getDeclaringClass();

            System.out.println(aClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
