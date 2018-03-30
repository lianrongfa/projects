package cn.lianrf.thread;

/**
 * Created by lianrongfa on 2018/3/28.
 */
public class TestApp {

    public static void main(String[] args) {
        test3();
    }

    private static void test1() {
        Source source = new Source();

        new Thread(()->{
            source.write("test");
        }).start();

        new Thread(()->{
            String read = source.read();
        }).start();
    }

    private static void test2() {
        Source source = new Source();

        new Thread(()->{
            source.writeLock("test");
        }).start();

        new Thread(()->{
            String read = source.readLock();
        }).start();
    }

    private static void test3() {
        SourceVolatile source = new SourceVolatile();

        new Thread(()->{
            source.read();
        }).start();

        new Thread(()->{
            source.write();
        }).start();
    }
}
