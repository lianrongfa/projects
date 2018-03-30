package cn.lian.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by lianrongfa on 2018/3/23.
 */
@Service
public class SpringBeanUtils implements ApplicationContextAware{

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return context;
    }
}
