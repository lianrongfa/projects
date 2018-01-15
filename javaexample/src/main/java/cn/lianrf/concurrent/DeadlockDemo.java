package cn.lianrf.concurrent;

import java.util.Map;

/**
 * Created by lianrongfa on 2018/1/15.
 */
public class DeadlockDemo {
    private static Object _1LOCK=new Object();
    private static Object _2LOCK=new Object();

    public static void main(String[] args) {
        new DeadlockDemo().test();
    }

    private void test() {
        new Thread(()->{
            synchronized (_1LOCK){
                try {
                    System.out.println("_1Lock Sleep");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("_1Lock weak wait！");
                synchronized (_2LOCK){

                }
            }
        },"haha1").start();

        new Thread(()->{
            synchronized (_2LOCK){
                try {
                    System.out.println("_2Lock Sleep");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("_2Lock weak wait！");
                synchronized (_1LOCK){

                }
            }
        },"haha2").start();

        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
