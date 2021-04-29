package cn.lianrf.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程A B C，A负责发任务序号1、2、3…，BC线程负责处理、B处理偶数任务、C处理奇数任务、怎么保证任务顺序执行？
 * condition实现
 * @version: v1.0
 * @date: 2021/4/29
 * @author: lianrf
 */
public class ThreadSequenceCondition {

    public static void main(String[] args){
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionB = reentrantLock.newCondition();
        Condition conditionC = reentrantLock.newCondition();
        int i=1;
        ThreadRun b = new ThreadRun("B", i, conditionB, reentrantLock);
        ThreadRun c = new ThreadRun("C", i, conditionC, reentrantLock);
        b.start();
        c.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true){
            reentrantLock.lock();
            i++;
            if(i%2==0){
                b.queue=i;
                conditionB.signal();
                try {
                    //Thread.sleep(1000L); 防止当前线程未执行完，消费线程B/C已经执行完
                    /*  eg:
                    *   condition.await();//醒来
                        System.out.println(getName()+":"+queue);
                        condition.signal();
                    * */
                    Thread.sleep(1000L);
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                c.queue=i;
                conditionC.signal();

                try {
                    Thread.sleep(1000L);
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reentrantLock.unlock();
        }

    }


}
class ThreadRun extends Thread{
    protected Integer queue;

    private final Condition condition;

    private final ReentrantLock lock;

    public ThreadRun(String name, Integer queue, Condition condition,ReentrantLock lock) {
        super(name);
        this.queue = queue;
        this.condition = condition;
        this.lock=lock;
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                condition.await();
                System.out.println(getName()+":"+queue);
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }finally {
                lock.unlock();
            }

        }
    }
}