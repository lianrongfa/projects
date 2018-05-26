package cn.lianrf.rmi;

/**
 * Created by lianrongfa on 2018/5/15.
 *
 * VM: -ea
 */
public class TestAssert {

    public static void main(String[] args) {
        System.out.println("123");

        int a = 0;
        int b = 1;
        assert a == b; //需显示开启，默认为不开启状态
        assert a == b : "执行失败！";

        System.out.println("1234");
    }

    private static void test1(Boolean b) {
        assert b;
        System.out.println("false");

    }
}
