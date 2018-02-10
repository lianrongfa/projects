package cn.lianrf.oom;

/**
 * Created by lianrongfa on 2018/2/5.
 */
public class ConstantOOM {
    public static void main(String[] args) {
        for (int i=0;i<100000;i++){
            String s=String.valueOf(i);
        }
    }
}
