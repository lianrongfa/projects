package cn.lianrf.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lianrongfa on 2018/6/15.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            countDownLatch.countDown();
        }
    }
}
