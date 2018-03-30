package cn.lianrf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class Student implements Serializable{

    static int  a1=1;

    static TestFunction<String> a=(String s)->{
        s="fwef";
        System.out.println(s);
    };

    public static void main(String[] args) {

        List arrays = new ArrayList();

        arrays.forEach((x)->{
            System.out.println(x);
        });

    }

    public static void main1(TestFunction function) {
        Integer a=null;
        function.test(a);
    }
}