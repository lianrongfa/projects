package cn.lianrf.thread;

import java.io.IOException;

/**
 * Created by lianrongfa on 2018/9/4.
 */
public class InteruptDemo {

    volatile static int a=0;

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            while (true){

                try {
                    Thread.sleep(1000);

                    /*if(a==1){
                        Thread.interrupted();
                    }*/

                    System.out.println("2222"+Thread.currentThread().interrupted());

                } catch (InterruptedException e) {
                    System.out.println("触发中断");
                }

            }
        }
        );
        thread.start();

        while(true){
            try {
                System.in.read();
                thread.interrupt();
                System.out.println("12312"+thread.isInterrupted());





            } catch (IOException e) {

            }

        }


    }
}
