package cn.lianrf;

import cn.lianrf.gc.Aa;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

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

        Aa a2=new Aa();
        a2.setUuid("H123");
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

    }
}

