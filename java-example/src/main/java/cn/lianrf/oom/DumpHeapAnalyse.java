package cn.lianrf.oom;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * dump java堆用于分析oom示例
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @version: v1.0
 * @date: 2021/3/29
 * @author: lianrf
 */
public class DumpHeapAnalyse {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 10, 2, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>()
//                ,((r, executor) -> {})
        );

        TaskRun taskRun = new TaskRun(executor);
        taskRun.setDaemon(true);
        taskRun.start();

        taskRun.join();

        executor.shutdown();

    }

    static class TaskRun extends Thread{

        private final ThreadPoolExecutor executor;

        public TaskRun(ThreadPoolExecutor executor) {
            super("taskRun");
            this.executor = executor;
        }

        @Override
        public void run() {
            while (true){
//            TimeUnit.SECONDS.sleep(1);
                executor.submit(()->{

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        }
    }
}
