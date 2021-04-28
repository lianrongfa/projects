package cn.lianrf.thread;

/**
 *
 * 三个线程A B C，A负责发任务序号1、2、3…，BC线程负责处理、B处理偶数任务、C处理奇数任务、怎么保证任务顺序执行？
 * @version: v1.0
 * @date: 2021/4/28
 * @author: lianrf
 */
public class ThreadSequenceExe {

    public Integer source=-1;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{
            ThreadSequenceExe threadSequenceExe = new ThreadSequenceExe();
            try {
                threadSequenceExe.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("my-ThreadA");
        thread.start();

    }

    public void start() throws InterruptedException {

        ThreadSequenceExe lock=this;

        Thread threadB = new Thread(()->{
            synchronized (lock){
                while (true){
                    if(source%2==0){
                        System.out.println("B get source:"+source);
                        source=-1;
                    }
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        });
        threadB.setName("my-ThreadB");
        Thread threadC = new Thread(()->{
            while (true) {
                synchronized (lock) {
                    if (source % 2 == 1) {
                        System.out.println("c get source:" + source);
                        source = -1;
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        threadB.setName("my-ThreadC");
        threadB.start();
        threadC.start();

        for (int i = 1; i < 100; i++) {
            synchronized (lock){
                if(source==-1){
                    source=i;
                    lock.notifyAll();
                    lock.wait();
                }
            }
        }


    }

}
