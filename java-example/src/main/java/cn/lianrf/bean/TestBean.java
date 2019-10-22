package cn.lianrf.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @version: v1.0
 * @date: 2019/10/21
 * @author: lianrf
 */
@Component
@Lazy
public class TestBean implements BeanNameAware, InitializingBean {


    private Entity entity;

    @Resource
    public void setEntity(Entity entity) {
        System.out.println("类属性初始化");
        this.entity = entity;
    }

    public TestBean() {
        System.out.println("构造器初始化");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name 初始化：" + name);
    }

    @PostConstruct
    public void test() {
        System.out.println("@PostConstruct构造器初始化后test");
    }

    @PostConstruct
    public void test3() {
        System.out.println("@PostConstruct构造器初始化后test3");
    }
    @PreDestroy
    public void test2() {
        System.out.println("@PreDestroy销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
