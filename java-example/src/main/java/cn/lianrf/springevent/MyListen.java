package cn.lianrf.springevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lianrongfa on 2017/12/20.
 */
@Service
public class MyListen implements ApplicationListener<MyEvent> {
    public void onApplicationEvent(MyEvent event) {
        test(event);
    }

    public void test(MyEvent event){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> resource = event.getResource();
        for (int i=0;i<resource.size();i++){
            System.out.println("MyListen-------------:"+resource.get(i));
            resource.remove(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()+"listen");
    }
}
