package cn.lianrf.gc;

/**
 * 超过指定大小的内存直接分配到老年代
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * -XX:+UseSerialGC
 * 使用ParallelGC时候 PretenureSizeThreshold参数无效
 * Created by lianrongfa on 2018/1/12.
 */
public class GcObjectSize {
    private static final int _1MB=1024*1024;

    public static void main(String[] args) {
        byte [] allocation=allocation=new byte[4*_1MB];
        /*byte [] allocation1=allocation=new byte[4*_1MB];
        byte [] allocation2=allocation=new byte[4*_1MB];*/
    }
}
