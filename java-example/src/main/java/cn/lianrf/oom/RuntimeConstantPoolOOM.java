package cn.lianrf.oom;

import java.util.ArrayList;

/**
 * Created by lianrongfa on 2018/1/9.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        test3();
    }

    private static void test1() {
        ArrayList<String> strings = new ArrayList<>();
        int i = 0;
        while (true) {
            strings.add(String.valueOf(i++).intern());
        }
    }

    private static void test2() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str3="计算机软件";
        System.out.println(str1.intern() == str3);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    private static void test3(){
        String str1="java";
        String str2=new String("java");
        System.out.println(str1==str2);
    }
}
