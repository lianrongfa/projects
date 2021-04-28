package cn.lianrf.springevent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;

/**
 * Created by lianrongfa on 2017/12/20.
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestApp {

    private String ss="";
    public TestApp() {
        System.out.println("TestApp construct init........");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String [] {"applicationContext.xml","applicationContext-threadpool.xml"});
        Object xixi = applicationContext.getBean("haha");
        System.out.println("over");
    }

    public static void test(ApplicationContext applicationContext) {
        MyPubisher service = applicationContext.getBean(MyPubisher.class);

        LinkedList<String> resource = new LinkedList<String>();
        resource.add("hehe");
        resource.add("haha");
        resource.add("xixi");
        MyEvent myEvent = new MyEvent(resource);
        service.sendMessage(myEvent);
        System.out.println(Thread.currentThread().getName()+":main");
    }


    @Autowired
    TestEntity testEntity;

    @Test
    public void junitTest(){
        System.out.println(testEntity);
    }

    public void test(){
        ss="123123";
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
}
