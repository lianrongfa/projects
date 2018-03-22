package cn.lianrf.cglib.lazy;

import net.sf.cglib.proxy.LazyLoader;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class ConcreteClassLazyLoader implements LazyLoader{
    @Override
    public Object loadObject() throws Exception {
        System.out.println("before LazyLoader...");
        PropertyBean propertyBean = new PropertyBean();
        System.out.println("after LazyLoader...");
        return propertyBean;
    }
}
