package cn.lianrf.demo.agent;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @version: v1.0
 * @date: 2020/11/25
 * @author: lianrf
 */
public class ApiTest {

    public static void main(String[] args){
        Annotation[] annotations = Bb.class.getAnnotations();

        for (Annotation annotation : annotations) {

            Annotation[] annotations1 = annotation.annotationType().getAnnotations();
            for (Annotation annotation1 : annotations1) {
                System.out.println(annotation1);
            }
        }
    }

    @Cacheable
    @Component
    static class Aa{

    }

    static class Bb extends Aa{

    }
}
