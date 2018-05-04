package cn.lianrf;

import java.io.Serializable;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class Student implements Serializable{

    static int  a1=1;

    static TestFunction<String> a3=(String s)->{
        s="fwef";
        System.out.println(s);
    };

    public static void main(String[] args) {
        Aa aa = new Aa();


    }

    public static void tset(Aa a){
        a=null;
    }

}
class Aa{
    public void test(){


    }
}