package cn.lianrf.javaagent;

import javassist.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
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

    /**
     * 开始3 with-api使用 此处为设置包名
     */
    public static void test3(){
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription superClass) {
                        return "i.love.ByteBuddy"+superClass.getSimpleName();
                    }
                })
                .subclass(Object.class)
                .make();
                //with-api
        //可以查看生成的class
        byte[] bytes = dynamicType.getBytes();
        File file = new File("C:\\Users\\86180\\Desktop\\test.class");
        outToFile(bytes, file);
    }

    /**
     * 开始4 rebase/
     */
    public static void test4(){
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .rebase(AgentTest.class)
                .defineField("test", String.class,Modifier.PUBLIC + Modifier.STATIC)
                .make();
        //可以查看生成的class

        byte[] bytes = dynamicType.getBytes();
        File file = new File("C:\\Users\\86180\\Desktop\\test.class");
        outToFile(bytes, file);
    }
    private static void outToFile(byte[] bytes, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(bytes);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        test4();
    }
}
