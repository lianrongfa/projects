package cn.lianrf.gc;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lianrongfa on 2018/1/15.
 */
public class NoSynTest {

    public static void main(String[] args) throws Exception {
        // 让jvivualvm有足够的时间做准备工作
        Thread.sleep(30000);

        final AtomicLong count = new AtomicLong(0);
        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    while(true) {
                        count.incrementAndGet();
                    }
                };
            }.start();
        }
    }
}
