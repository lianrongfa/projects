package cn.lianrf.springevent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lianrongfa on 2017/12/28.
 */
@Configuration
public class TestEntity {

    public TestEntity() {
        System.out.println("haha");
    }

    public static void main(String[] args) {
        System.out.println("0000000");
    }
    @Bean(name = "xixi")
    public TestApp test2(){
        return new TestApp();
    }
}
