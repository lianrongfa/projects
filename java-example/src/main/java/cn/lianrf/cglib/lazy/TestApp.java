package cn.lianrf.cglib.lazy;

/**
 * Created by lianrongfa on 2018/2/26.
 */
public class TestApp {
    public static void main(String[] args) {
        LazyBean bean = new LazyBean(1,"xx");
        bean.getPropertyBean().getKey();
        bean.getPropertyBean().getKey();
        System.out.println("-----分割线-----");
        bean.getPropertyBeanDispatcher().getKey();
        bean.getPropertyBeanDispatcher().getKey();
    }
}
