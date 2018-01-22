package cn.lianrf.springevent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * Created by lianrongfa on 2017/12/20.
 */
@Service
public class MyPubisher implements ApplicationContextAware {

    public MyPubisher() {
        System.out.println("MyPubisher construct init......");
    }

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public void sendMessage(ApplicationEvent event){
        applicationContext.publishEvent(event);
    }
}
