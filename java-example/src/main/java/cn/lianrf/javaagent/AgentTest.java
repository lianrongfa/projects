package cn.lianrf.javaagent;

import javassist.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @version: v1.0
 * @date: 2020/11/19
 * @author: lianrf
 */
public class AgentTest {
    /**
     * 开始1 第一个hello word
     */
    public static void test1() throws IllegalAccessException, InstantiationException, IOException {
        DynamicType.Unloaded<Object> make = new ByteBuddy()
                .subclass(Object.class)
                //包名+类名 小数点最后一个名称为类名
                .name("cn.lianrf.javaagent")
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make();
        //可以查看生成的class
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\86180\\Desktop\\test.class"))) {
            fileOutputStream.write(make.getBytes());
        }

        Object o = make.load(AgentTest.class.getClassLoader())
                .getLoaded()
                .newInstance();

        String helloWorld = o.toString();
        // Hello World!
        System.out.println(helloWorld);
    }

    /**
     * 开始2 利用字节码生成main方法
     */
    public static void test2() throws IllegalAccessException, InstantiationException, IOException {
        DynamicType.Unloaded<Object> make = new ByteBuddy()
                .subclass(Object.class)
                //包名+类名 小数点最后一个名称为类名
                .name("cn.lianrf.javaagent")
                .defineMethod("main",void.class,  Modifier.PUBLIC + Modifier.STATIC)
                .withParameter(String[].class,"args")
                .intercept(MethodDelegation.to(Hi.class))
                //.intercept(FixedValue.value("Hello World!"))
                .make();
        //可以查看生成的class
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\86180\\Desktop\\test.class"))) {
            fileOutputStream.write(make.getBytes());
        }
    }

    public static void main(String[] args) throws Exception {
        test2();
    }
}
