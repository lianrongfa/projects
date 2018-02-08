package cn.lianrf;

import org.junit.Test;

/**
 * Created by lianrongfa on 2018/1/2.
 */
public class Hello {



    static{
        System.out.println("Hello init");
    }
    final static Aa a=new Aa();
    public static void main(String args[]){

    }

}

class Aa{
    static{
        System.out.println("Aa init");
    }
}