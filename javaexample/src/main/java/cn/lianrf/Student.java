package cn.lianrf;

import java.io.Serializable;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class Student implements Serializable{
    private String a;
    private String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}