package cn.lianrf.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lianrongfa on 2018/1/12.
 */
public class CountDownLatchDemo {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        ObjectDemo objectDemo = new ObjectDemo(countDownLatch);
        for (int i = 0; i < 16; i++) {
            new Thread(objectDemo).start();
            countDownLatch.countDown();
        }
    }

}

class ObjectDemo implements Runnable {
    private static int i = 0;
    private CountDownLatch cdl;

    public ObjectDemo(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        System.out.println(i);

    }
}