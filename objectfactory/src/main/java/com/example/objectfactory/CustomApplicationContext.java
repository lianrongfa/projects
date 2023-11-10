package com.example.objectfactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lianrf
 * @version 1.0.0
 * @since 2023/11/10 10:32
 */
@Component
public class CustomApplicationContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext customApplicationContext) throws BeansException {
        this.applicationContext = customApplicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
