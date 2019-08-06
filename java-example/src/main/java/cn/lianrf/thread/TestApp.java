package cn.lianrf.thread;

/**
 * Created by lianrongfa on 2018/3/28.
 */
public class TestApp {

    static ThreadLocal threadLocal=new ThreadLocal();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        /*try {
            thread.join();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("main die");
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

    private static void test4(){
        new Thread(()->{
            Object o = threadLocal.get();
        }).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            Object o = threadLocal.get();
        }).start();
    }
}
