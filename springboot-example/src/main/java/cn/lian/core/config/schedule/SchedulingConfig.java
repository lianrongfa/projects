package cn.lian.core.config.schedule;

import cn.lian.core.util.SpringBeanUtils;
import cn.lian.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by lianrongfa on 2018/3/23.
 * spring集成的简易定时器
 */

//@Configuration
//@EnableScheduling
public class SchedulingConfig {



    @Scheduled(cron = "0/30 * * * * ?")
    public void test(){
        System.out.println("定时器测试");
        ApplicationContext applicationContext = SpringBeanUtils.getApplicationContext();
        Object schedu = applicationContext.getBean("schedu");

        System.out.println("name"+schedu);
    }
    //@Bean("schedu")
    //@Scope("request")
    public User buildUser(){
        return new User();
    }
}
