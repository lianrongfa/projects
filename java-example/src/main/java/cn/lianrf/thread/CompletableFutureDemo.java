package cn.lianrf.thread;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * @author lianrongfa
 * @date 2018/7/23
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {


        CompletableFuture completableFuture = new CompletableFuture();


        CompletableFuture.
            runAsync(null).
            thenRun(null).
            whenComplete(new BiConsumer<Void, Throwable>() {//完成反馈
                @Override
                public void accept(Void aVoid, Throwable throwable) {

                }
            }).join();//等待完成
    }
}
