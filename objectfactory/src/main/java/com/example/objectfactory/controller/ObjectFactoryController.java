package com.example.objectfactory.controller;

import com.example.objectfactory.CustomApplicationContext;
import com.example.objectfactory.ObjectFactoryApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lianrf
 * @version 1.0.0
 * @since 2023/11/09 18:52
 */
@RestController
@RequestMapping("/obj")
public class ObjectFactoryController {

    @Autowired
    private ObjectFactory<HttpServletRequest> objectFactory;

    @Autowired
    private ObjectFactory<CustomClassA> customClassAObjectFactory;

    @Autowired
    private CustomApplicationContext context;

    @GetMapping("objF")
    public void testObjF(){
        System.out.println(objectFactory.getObject());
    }

    @GetMapping("registerObjF")
    public void testObj(){

        ApplicationContext applicationContext = context.getApplicationContext();

        ConfigurableListableBeanFactory beanFactory = ((AnnotationConfigServletWebServerApplicationContext) applicationContext).getBeanFactory();

        beanFactory.registerResolvableDependency(CustomClassA.class,new CustomClassAFactory());


        System.out.println("动态注入bean成功");
    }

    @GetMapping("testRegisterObjF")
    public void testRegisterObjF(){
        CustomClassA object = customClassAObjectFactory.getObject();
        System.out.println(object.getName());
    }

    public static class CustomClassA{
        private String name;

        public CustomClassA(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CustomClassAFactory implements ObjectFactory<CustomClassA>{
        @Override
        public CustomClassA getObject() throws BeansException {
            return new CustomClassA("123");
        }
    }
}
