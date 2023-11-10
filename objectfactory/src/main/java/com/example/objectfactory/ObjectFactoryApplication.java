package com.example.objectfactory;

import com.example.objectfactory.controller.ObjectFactoryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * 用于验证{@link org.springframework.beans.factory.ObjectFactory}功能验证
 *<p>
 *     <a href='https://blog.csdn.net/qq_41907991/article/details/105123387'>参考链接</a>
 *</p>
 *
 * ObjectFactory可以用于延迟注入
 *
 * 第一步动态注入：{@link ObjectFactoryController#testObj()}
 * 第二步动态使用：{@link ObjectFactoryController#customClassAObjectFactory}
 *
 */
@SpringBootApplication
public class ObjectFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectFactoryApplication.class, args);
    }


    public static class Message{
        {
            System.out.println("123");
        }
    }

    @Bean
    public Message init(){
        return new Message();
    }

}
