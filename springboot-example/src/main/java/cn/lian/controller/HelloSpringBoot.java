package cn.lian.controller;

import cn.lian.core.ITest;
import cn.lian.core.config.datasource.DataSourceEnum;
import cn.lian.core.config.datasource.DynamicDatasource;
import cn.lian.core.mvcresult.Result;
import cn.lian.entity.Test;
import cn.lian.entity.User;
import cn.lian.service.HelloSerivce;
import cn.lian.service.UseCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianrongfa on 2018/1/19.
 */
@RestController
@RequestMapping("/root")
@Api(value = "test",description = "gwew")
public class HelloSpringBoot {

    @Autowired
    List<ITest> list;

    @Autowired
    private HelloSerivce service;

    @Autowired
    private UseCacheService cacheService;

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @RequestMapping("/hello1")
    public User hello1(){
        service.selectByPrimaryKey("1");
        System.out.println("hello111!");
        return new User();
    }

    @RequestMapping("/dataSource1")
    public User dataSource1(){
        User user = service.selectByPrimaryKey1("1");
        return user;
    }

    @RequestMapping("/dataSource2")
    public User dataSource2(){
        //User user = service.selectByPrimaryKey2("1");
        User user=new User();
        Test test1 = new Test();
        test1.settName("123");
        Test test2 = new Test();
        test2.settName("123");
        ArrayList<Test> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        user.setTestList(list);
        return user;
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
