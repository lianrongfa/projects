package cn.lianrf.classloader;

/**
 * Created by lianrongfa on 2018/1/10.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println(Aa.aa);
    }
}
class Aa{
    public static  String aa="1";

    static {
        System.out.println("im init");
    }

}