package cn.lianrf.service.loader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @version: v1.0
 * @date: 2019/10/12
 * @author: lianrf
 */
public class LoaderImpl2<T> implements LoaderInterface<T>{
    public LoaderImpl2() {
        System.out.println("LoaderImpl2");
    }

    @Override
    public void test(T s) {
        Class<?> aClass = s.getClass();
        Class<Object> type = getSuperClassGenricType(getClass(), 0);
        System.out.println(aClass==type);
    }

    private Class<Object> getSuperClassGenricType(final Class clazz, final int index) {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
