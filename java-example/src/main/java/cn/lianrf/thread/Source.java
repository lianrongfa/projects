package cn.lianrf.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lianrongfa on 2018/3/28.
 *
 * 持有同一个锁的两个代码块，线程只能串行执行！！
 */
public class Source {

    private String item;

    public void write(String item){
        synchronized (this){
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("write:"+item);
            this.item=item;
        }
    }

    public String read(){
        synchronized (this){
            System.out.println("read:"+item);
            return this.item;
        }
    }

    private static Lock lock=new ReentrantLock();


    public void writeLock(String item){
        lock.lock();
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("write:"+item);
            this.item=item;
        lock.unlock();
    }

    public String readLock(){
        lock.lock();
            System.out.println("read:"+item);
        lock.unlock();
            return this.item;

    }
}
