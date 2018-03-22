package cn.lianrf;

import java.io.Serializable;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class Student implements Serializable{
    public void test(){
        new Thread(()->{
            System.out.println();
        });
    }

    public static void main(String[] args) {
        String s="fwfwe.gweg.weg.gwegwe";
        String[] split=s.split("\\.");
        System.out.println(split[0]);
    }
}