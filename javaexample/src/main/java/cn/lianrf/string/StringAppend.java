package cn.lianrf.string;

/**
 * Created by lianrongfa on 2018/1/20.
 */
public class StringAppend {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        test1();
        System.out.println(System.currentTimeMillis()-l);
    }

    private static void test1() {
        String s = "aa" + "bb" + "cc" + "aa" + "bb" + "cc"+"aa" + "bb" + "cc" + "aa" + "bb" + "cc"+"aa" + "bb" + "cc" + "aa" + "bb" + "cc"+"aa" + "bb" + "cc" + "aa" + "bb" + "cc";
    }
    private static void test2() {
        new StringBuffer("aa").append("bb").append("cc").append("aa").append("bb").append("cc");
    }
}
