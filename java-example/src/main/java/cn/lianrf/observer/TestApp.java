package cn.lianrf.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lianrongfa on 2017/11/10.
 */
public class TestApp {
    public static void main(String[] args) throws InterruptedException {
        //test1();
        List l=new ArrayList();
        l.add("cxy");
        l.add("cy");
        l.add("123");
        List list = l.subList(l.size()-1, l.size());
        System.out.println(list.size());

    }

    private static void test2() {
        HashMap hashMap = new HashMap();
        hashMap.put("1","1");
        hashMap.put("2",2);

        String o1 = (String)hashMap.get("1");
        String o2 = (Integer)hashMap.get("2")+"";
        Object o = hashMap.get("3");
        System.out.println("1".equals(o1));
        System.out.println("2".equals(o2));
        System.out.println(o);
    }

    private static void test1() throws InterruptedException {
        SubjectImpl subject = new SubjectImpl();

        WeObServer haha = new WeObServer("haha");
        WeObServer hehe = new WeObServer("hehe");
        WeObServer xixi = new WeObServer("xixi");

        subject.registerObServers(haha);
        subject.registerObServers(hehe);
        subject.registerObServers(xixi);

        subject.setNum(11);

        while (true){
            System.out.println("size:"+Thread.getAllStackTraces().size());
            Thread.sleep(1000);
            subject.notifyObservers();

        }
    }
}
