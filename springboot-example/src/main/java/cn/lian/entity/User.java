package cn.lian.entity;

import org.junit.Test;

import java.util.Date;

public class User {
    private String id;//这是id

    private String name;

    private Integer age;

    private Date birthday;

    private String student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student == null ? null : student.trim();
    }

    /**
     * Created by lianrongfa on 2018/2/5.
     */
    public static class TestApp {

        @Test
        public void test2(){



        }

    }
}