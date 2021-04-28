package cn.lianrf.springcglib;

import org.springframework.stereotype.Component;

/**
 * Created by lianrongfa on 2017/11/6.
 */
@Component("student")
public class Student implements IStudent {
    private String name;
    @MyAop
    public void say(){
        System.out.println("my name is lianrf!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

