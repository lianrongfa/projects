package cn.lianrf;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lianrongfa on 2018/6/20.
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("dubbo-consumer.xml");

        IUserService userService = (IUserService) applicationContext.getBean("userServiceImpl");

        System.out.println(userService.insert("hello"));
    }
}
