package cn.lianrf.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @version: v1.0
 * @date: 2019/10/21
 * @author: lianrf
 */
@Component
public class TestPost implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if ("testBean".equals(beanName)||this.getClass().equals(bean.getClass())) {
            System.out.println("postProcessBeforeInitialization:" + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("testBean".equals(beanName)||this.getClass().equals(bean.getClass())) {
            System.out.println("postProcessAfterInitialization:" + beanName);
        }
        return bean;
    }

}
