package cn.lianrf.java8stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @version: v1.0
 * @date: 2021/2/9
 * @author: lianrf
 */
public class ParallelStream {
    /*
    * 可以可以通过设置JVM系统属性：-Djava.util.concurrent.ForkJoinPool.common.parallelism=N （N为线程数量）
    */


    public static void main(String[] args) throws InterruptedException {

        List<Integer> source = buildSource();

        // 统计并行执行list的线程
        //-Djava.util.concurrent.ForkJoinPool.common.parallelism=1
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();

        source.parallelStream().forEach(
                i -> {
                    threadSet.add(Thread.currentThread());
                }
        );

        System.out.println("threadSet一共有" + threadSet.size() + "个线程");
        System.out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");

    }














    //创建资源
    private static ArrayList<Integer> buildSource() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        return list;
    }
}
