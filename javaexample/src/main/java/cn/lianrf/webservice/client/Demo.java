package cn.lianrf.webservice.client;

/**
 * Created by lianrongfa on 2018/5/15.
 */
public class Demo {
    public static void main(String[] args) {
        SayHelloImplService sayHelloImplService = new SayHelloImplService();
        SayHelloImpl implPort = sayHelloImplService.getSayHelloImplPort();
        System.out.println(implPort.say1("tom"));
        System.out.println( implPort.say2("tom"));

    }
}
