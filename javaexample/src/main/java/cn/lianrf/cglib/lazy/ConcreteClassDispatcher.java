package cn.lianrf.cglib.lazy;

import net.sf.cglib.proxy.Dispatcher;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class ConcreteClassDispatcher implements Dispatcher{
    @Override
    public Object loadObject() throws Exception {
        System.out.println("before Dispatcher...");
        PropertyBean propertyBean = new PropertyBean();
        System.out.println("after Dispatcher...");
        return propertyBean;

    }
}
