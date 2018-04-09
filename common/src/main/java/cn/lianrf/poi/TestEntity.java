package cn.lianrf.poi;

import java.util.Date;

/**
 * Created by lianrongfa on 2018/3/23.
 */
public class TestEntity {

    private String name;
    private String num;
    private String age;
    private Date date=new Date();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }
}
