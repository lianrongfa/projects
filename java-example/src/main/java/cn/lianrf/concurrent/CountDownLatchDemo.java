package cn.lianrf.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lianrongfa on 2018/1/12.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        while(true){
            Thread.sleep(5000);
        }

    }

}
class ObjectDemo implements Runnable{
    private static int i=0;
    public void run() {
            i++;
            System.out.println(i);
    }
}