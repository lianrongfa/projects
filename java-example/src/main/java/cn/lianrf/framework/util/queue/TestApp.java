package cn.lianrf.framework.util.queue;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lianrongfa on 2018/2/1.
 */
public class TestApp {
    public static void main(String[] args) {
        CacheQueue<String> queue = new CacheQueue<>();

        for (int i=0;i<10000;i++){
            queue.add(String.valueOf(i),1);
        }
        long l = System.currentTimeMillis();
        try {
            Thread.sleep(59950);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i=0;i<10;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(true){
                    System.out.println("取得："+queue.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            countDownLatch.countDown();
        }

        System.out.println("main:"+(System.currentTimeMillis()-l));
    }
}
