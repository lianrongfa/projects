package cn.lianrf.cglib.lazy;

/**
 * 使用cglib进行懒加载 对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
 * 在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了（在CGLib的实现中只要去访问该对象内属性的getter方法，
 * 就会自动触发代理类回调）。
 * Created by lianrongfa on 2018/2/24.
 */

/**
 * Dispatcher和LazyLoader的区别在于：LazyLoader只在第一次访问延迟加载属性时触发代理类回调方法，
 * 而Dispatcher在每次访问延迟加载属性时都会触发代理类回调方法。
 */

public class LazyBean {
    private int age;
    private String name;
    private PropertyBean propertyBean;
    private PropertyBean propertyBeanDispatcher;


    public LazyBean(int age, String name) {
        this.age = age;
        this.name = name;
        this.propertyBean=null;
        this.propertyBeanDispatcher=null;
    }


    public PropertyBean createLazyBean(){
        new ConcreteClassLazyLoader();
        return null;
    }

    public PropertyBean createDispacher(){
        new ConcreteClassDispatcher();
        return null;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }

    public PropertyBean getPropertyBeanDispatcher() {
        return propertyBeanDispatcher;
    }

    public void setPropertyBeanDispatcher(PropertyBean propertyBeanDispatcher) {
        this.propertyBeanDispatcher = propertyBeanDispatcher;
    }

    @Override
    public String toString() {
        return "LazyBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", propertyBean=" + propertyBean +
                ", propertyBeanDispatcher=" + propertyBeanDispatcher +
                '}';
    }
}
