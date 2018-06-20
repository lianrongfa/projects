package cn.lianrf;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by lianrongfa on 2018/6/19.
 */
public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("dubbo-provider.xml");
        applicationContext.start();
        System.in.read();
    }
}
