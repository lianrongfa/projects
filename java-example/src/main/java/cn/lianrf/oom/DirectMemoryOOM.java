package cn.lianrf.oom;

import sun.misc.Unsafe;

/**
 * Created by lianrongfa on 2018/1/9.
 */
public class DirectMemoryOOM {
    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        unsafe.allocateMemory(1l);
    }
}
