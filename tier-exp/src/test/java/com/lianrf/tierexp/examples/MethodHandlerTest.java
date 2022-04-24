package com.lianrf.tierexp.examples;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * MethodHandlerTest
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/4/22 5:43 下午
 */
public class MethodHandlerTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {

        MethodHandles.Lookup lookup = MethodHandles.publicLookup();

        MethodType methodType = MethodType.methodType(Object.class,Object.class);

        MethodHandle test = lookup.findVirtual(MethodHandlerTest.class, "test", methodType);

        System.out.println(test);
    }


    public void test(Object o){
    }
}
