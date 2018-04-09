package cn.lian.controller;

import cn.lian.core.config.datasource.DataSourceEnum;
import cn.lian.core.config.datasource.DynamicDatasource;
import cn.lian.entity.Test;
import cn.lian.entity.User;
import cn.lian.service.HelloSerivce;
import cn.lian.service.UseCacheService;
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

    @Autowired
    private UseCacheService cacheService;

    @RequestMapping("/hello1")
    public User hello1(){
        service.checkN_1();
        System.out.println("hello111!");
        return new User();
    }

    @RequestMapping("/hello2")
    public User hello2(){
        DynamicDatasource.setDataSource(DataSourceEnum.SLAVE.getName());
        service.insert();
        System.out.println("hello111!");
        return new User();
    }

    @RequestMapping("/cache")
    public Test cache(String id){
        Test test = cacheService.selectByPrimaryKey(id);
        return test;
    }
}
