package cn.lianrf.springevent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;

/**
 * Created by lianrongfa on 2017/12/20.
 */

public class TestApp {

    public static String ss="";
    public TestApp() {
        System.out.println("TestApp construct init........");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String [] {"applicationContext.xml","applicationContext-threadpool.xml"});
        Object xixi = applicationContext.getBean("haha");
        System.out.println("over");
    }

    private static void test(ApplicationContext applicationContext) {
        MyPubisher service = applicationContext.getBean(MyPubisher.class);

        LinkedList<String> resource = new LinkedList<String>();
        resource.add("hehe");
        resource.add("haha");
        resource.add("xixi");
        MyEvent myEvent = new MyEvent(resource);
        service.sendMessage(myEvent);
        System.out.println(Thread.currentThread().getName()+":main");
    }

    public void test(){
        ss="123123";
    }

}
