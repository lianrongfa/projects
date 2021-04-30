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

    private static void test123() throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();

        Condition condition = reentrantLock.newCondition();

        new Thread(()->{

            reentrantLock.lock();
            try {
                System.out.println("child get lock");
                Thread.sleep(1);
                condition.signal();

                System.out.println("child weak up");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            reentrantLock.unlock();
            System.out.println("child thread die");
        }).start();

        reentrantLock.lock();

        System.out.println("main lock");
        condition.signal();
        Thread.sleep(1000);
        System.out.println("signal");
        System.out.println("await");
        condition.await();

        reentrantLock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }



    private static void test() {
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
            System.out.println(Thread.currentThread().getName()+"get lock");
            if(i%2==0){
                b.queue=i;
                try {
                    conditionB.signal();
                    System.out.println("A生成："+i);
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                c.queue=i;
                try {
                    conditionC.signal();
                    System.out.println("A生成："+i);
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
            System.out.println(getName()+"get lock");
            try {
                // 当signal后，A线程抢到了lock，该线程阻塞到lock.lock(),conditionB.signal未唤醒任何线程，故整个过程一直阻塞
                condition.await(); //bug in there 此处应注释

                System.out.println(getName()+":"+queue);
                condition.signal();
//                condition.await();//此处打开
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }finally {
                lock.unlock();
            }

        }
    }
}