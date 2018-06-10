package cn.lianrf;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by lianrongfa on 2018/6/10.
 */
public class DistributedLock implements Lock,Watcher{
    public void lock() {

    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {

    }

    public Condition newCondition() {
        return null;
    }

    public void process(WatchedEvent event) {

    }
}
