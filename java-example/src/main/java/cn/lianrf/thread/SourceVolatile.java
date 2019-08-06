package cn.lianrf.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lianrongfa on 2018/3/28.
 * <p>
 *volatile 写操作先行与读操作测试
 */
public class SourceVolatile {

    private volatile Boolean b=false;

    public void write() {

        try {
            Thread.sleep(1000);
            System.out.println(this.b);
            this.b=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        while (!b){

        }
        System.out.println("read:"+this.b);
    }

}
