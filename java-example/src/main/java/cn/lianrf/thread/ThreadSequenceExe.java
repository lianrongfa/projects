package cn.lianrf.thread;

/**
 *
 * 三个线程A B C，A负责发任务序号1、2、3…，BC线程负责处理、B处理偶数任务、C处理奇数任务、怎么保证任务顺序执行？
 * @version: v1.0
 * @date: 2021/4/28
 * @author: lianrf
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 三个线程A B C，A负责发任务序号1、2、3…，BC线程负责处理、B处理偶数任务、C处理奇数任务、怎么保证任务顺序执行？
 */
public class ThreadSequenceExe {


    private int source=-1;


    public static void main(String[] args) {

    }

    public void test2(){

        ReentrantLock reentrantLock = new ReentrantLock();

        Condition even = reentrantLock.newCondition();

        Condition odd = reentrantLock.newCondition();


        int i=0;
        while (i<100){

            reentrantLock.lock();




        }



    }


    /**
     * 方法一 synchronized 实现
     */
    public void test1(){
        ThreadSequenceExe lock = this;

        new Thread(()->{
            while (true){
                synchronized (lock){
                    if(lock.source%2==0&&lock.source!=0){
                        System.out.println("B:"+lock.source);
                        if(lock.source==98){
                            reset();
                            return;
                        }
                        reset();
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(()->{
            while (true){
                synchronized (lock){
                    if(lock.source%2==1){
                        System.out.println("A:"+lock.source);
                        if(lock.source==99){
                            lock.notifyAll();
                            return;
                        }
                        reset();
                    }

                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        int i=0;
        while (i<99) {
            synchronized (lock){
                if(lock.source==-1){
                    i++;
                    lock.source=i;
                }
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void reset(){
        this.source=-1;
    }

}