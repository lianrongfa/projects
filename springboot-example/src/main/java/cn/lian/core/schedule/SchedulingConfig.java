package cn.lian.core.schedule;

import cn.lian.core.util.SpringBeanUtils;
import cn.lian.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by lianrongfa on 2018/3/23.
 */

@Configuration
@EnableScheduling
public class SchedulingConfig {



    @Scheduled(cron = "0/30 * * * * ?")
    public void test(){
        System.out.println("定时器测试");
        ApplicationContext applicationContext = SpringBeanUtils.getApplicationContext();
        Object schedu = applicationContext.getBean("schedu");

        System.out.println("name"+schedu);
    }
    @Bean("schedu")
    @Scope("request")
    public User buildUser(){
        return new User();
    }
}
