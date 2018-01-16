package cn.lianrf;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by lianrongfa on 2018/1/2.
 */
public class Test1 {
    /*@Test
    public void test() {
        System.out.println("111");
    }*/


    public static void main(String args []) {

        Aa a1=new Aa();
        a1.setName("haha");
        a1.setUuid("H123");
        Aa a2=new Aa();

        //BeanUtils.copyProperties(a2,a1);
        try {
            PropertyUtils.copyProperties(a2,a1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(a1.toString());
        System.out.println(a2.toString());
        System.out.println(a2.getClass());
        System.out.println(a1.getClass());
    }
}

class Aa {
    private String uuid;
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Aa{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}