package cn.lianrf.springevent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by lianrongfa on 2017/12/28.
 */
@Configuration
public class TestEntity {

    private String name;
    private List<String>[] names;
    public TestEntity() {
        System.out.println("haha");
    }

    public static void main(String[] args) {
        System.out.println("0000000");
    }
    //@Bean(name = "xixi")
    public TestApp test2(){
        return new TestApp();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String>[] getNames() {
        return names;
    }

    public void setNames(List<String>[] names) {
        this.names = names;
    }
    @PostConstruct
    public void post(){
        System.out.println("!~~~~~~~~~~~post");
    }

    @Bean/*(initMethod = "post",destroyMethod = "post")*/
    public TestEntity beans(){
        return new TestEntity();
    }

}
