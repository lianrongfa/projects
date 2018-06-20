package cn.lianrf;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by lianrongfa on 2018/6/20.
 */
public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("dubbo-consumer.xml");

        IUserService userService = (IUserService) applicationContext.getBean("userServiceImpl");

        System.out.println(userService.insert("hello"));

        System.in.read();
    }
}
