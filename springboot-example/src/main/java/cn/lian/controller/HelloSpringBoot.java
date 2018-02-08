package cn.lian.controller;

import cn.lian.entity.User;
import cn.lian.service.HelloSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lianrongfa on 2018/1/19.
 */
@RestController
@RequestMapping("/root")
public class HelloSpringBoot {
    @Autowired
    private HelloSerivce service;

    @RequestMapping("/hello")
    public User hello(){
        service.insert();
        System.out.println("hello111!");
        return new User();
    }
}
