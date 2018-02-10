package cn.lianrf;

import org.junit.Test;

/**
 * Created by lianrongfa on 2018/1/2.
 */
public class Hello {

    public static void main(String args[]){
        Aa aa = new Aa();
        aa.create2();
    }

}

class Aa{
    public Aa create1(){
        return new Aa();
    }

    public Aa create2(){
        return create1();
    }
}