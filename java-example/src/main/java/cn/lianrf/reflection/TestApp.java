package cn.lianrf.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lianrongfa on 2018/3/30.
 */
public class TestApp {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        FinalField obj = new FinalField();

        getField(obj, "testFinal");
        getField(obj, "testStaticFinal");
    }

    private static void getField(FinalField obj, String fieldName) {
        try {
            Field testFinal = FinalField.class.getDeclaredField(fieldName);
            testFinal.setAccessible(true);
            System.out.println(testFinal.get(obj));

            testFinal.set(obj,"改变");

            System.out.println(testFinal.get(obj));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void test1() {
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
